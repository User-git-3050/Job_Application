package com.example.msjob.service.impl;

import com.example.msjob.client.UserClient;
import com.example.msjob.dao.request.JobRequest;
import com.example.msjob.dao.request.JobSearchCriteria;
import com.example.msjob.dao.response.CompanyUserResponse;
import com.example.msjob.dao.response.JobResponse;
import com.example.msjob.dao.response.UserResponse;
import com.example.msjob.entity.CompanyEntity;
import com.example.msjob.entity.JobEntity;
import com.example.msjob.entity.JobSkillEntity;
import com.example.msjob.event.JobApplicationEvent;
import com.example.msjob.exception.NotFoundException;
import com.example.msjob.exception.UnauthorizedException;
import com.example.msjob.repository.CompanyRepository;
import com.example.msjob.repository.JobRepository;
import com.example.msjob.repository.JobSkillRepository;
import com.example.msjob.service.JobService;
import com.example.msjob.specification.JobSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.msjob.enums.ErrorMessage.COMPANY_NOT_FOUND_WITH_ID;
import static com.example.msjob.enums.ErrorMessage.JOB_NOT_FOUND;
import static com.example.msjob.enums.InfoMessage.*;
import static com.example.msjob.mapper.JobMapper.*;
import static com.example.msjob.mapper.JobSkillMapper.JOB_SKILL_MAPPER;
import static java.lang.String.format;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserClient userClient;
    private final CompanyRepository companyRepository;
    private final JobSkillRepository jobSkillRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, UserClient userClient, CompanyRepository companyRepository, JobSkillRepository jobSkillRepository) {
        this.jobRepository = jobRepository;
        this.userClient = userClient;
        this.companyRepository = companyRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream().map(JOB_MAPPER::mapToResponse).toList();
    }


    @Override
    public JobResponse getJobById(Long id) {
        return jobRepository.findById(id).map(JOB_MAPPER::mapToResponse).orElseThrow(() -> new NotFoundException(
                format(
                        JOB_NOT_FOUND.getMessage(),
                        id
                )
        ));
    }

    @Override
    public String createJob(JobRequest jobRequest, String username) {
        CompanyUserResponse companyUserResponse = userClient.findCompanyUserByUsername(username).getBody();

        Long recruiterId = companyUserResponse.getUserId();
        Long companyId = companyUserResponse.getCompanyId();

        if (companyId == null) {
            JobEntity jobEntity= JOB_MAPPER.mapToEntity(jobRequest,null,recruiterId);
            jobEntity = jobRepository.save(jobEntity);

            jobSkillRepository.saveAll(JOB_SKILL_MAPPER.mapToEntity(jobRequest.getSkillIds(),jobEntity));
        } else {
            CompanyEntity companyEntity = companyRepository.findById(companyId)
                    .orElseThrow(() -> new NotFoundException(
                            format(
                                    COMPANY_NOT_FOUND_WITH_ID.getMessage(),
                                    companyId
                            )
                    ));
            JobEntity jobEntity = JOB_MAPPER.mapToEntity(jobRequest,companyEntity,recruiterId);
            jobEntity = jobRepository.save(jobEntity);

            jobSkillRepository.saveAll(JOB_SKILL_MAPPER.mapToEntity(jobRequest.getSkillIds(),jobEntity));


        }

        return JOB_CREATED.getMessage();
    }

    @Override
    public String updateJobById(Long id, JobRequest jobRequest, String username) {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();

        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                JOB_NOT_FOUND.getMessage(),
                                id
                        )
                ));


        if (userResponse != null && hasPermission(userResponse, jobEntity)) {

            List<JobSkillEntity> jobSkillEntities = JOB_SKILL_MAPPER.mapToEntity(jobRequest.getSkillIds(), jobEntity);

            jobEntity.setTitle(jobRequest.getTitle());
            jobEntity.setDescription(jobRequest.getDescription());
            jobEntity.setLocation(jobRequest.getLocation());
            jobEntity.setMinSalary(jobRequest.getMinSalary());
            jobEntity.setMaxSalary(jobRequest.getMaxSalary());
            jobEntity.setType(jobRequest.getType());

            jobRepository.save(jobEntity);
            jobSkillRepository.saveAll(jobSkillEntities);

            return JOB_UPDATED.getMessage();
        }
        throw new UnauthorizedException(NO_PERMISSION.getMessage());


    }


    @Override
    public String deleteJobById(Long id, String username) {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();

        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                JOB_NOT_FOUND.getMessage(),
                                id
                        )
                ));

        if (userResponse != null && hasPermission(userResponse, jobEntity)) {
            jobRepository.delete(jobEntity);
            jobSkillRepository.deleteAllByJobId(id);
            return JOB_DELETED.getMessage();
        }
        throw new UnauthorizedException(NO_PERMISSION.getMessage());


    }

    @Override
    public List<JobResponse> searchJobs(String keyword) {
        return jobRepository.searchJobEntities(keyword).stream()
                .map(JOB_MAPPER::mapToResponse).toList();
    }


    @Override
    public Boolean existsJobById(Long id) {
        return jobRepository.existsById(id);
    }

    @Override
    public List<JobResponse> searchJobsByCriteria(JobSearchCriteria jobSearchCriteria) {
        JobSpecification specification = new JobSpecification(jobSearchCriteria);
        List<JobEntity> jobs = jobRepository.findAll(specification);

        return jobs.stream().map(JOB_MAPPER::mapToResponse).toList();
    }


    @KafkaListener(
            topics = "job-application-topic",
            groupId = "job-service"
    )
    @Transactional
    public void incrementApplicationCount(JobApplicationEvent jobApplicationEvent){
        jobRepository.incrementApplicationCount(jobApplicationEvent.getJobId());
    }


    private Boolean hasPermission(UserResponse userResponse, JobEntity jobEntity) {
        return userResponse.getRole().equals("ADMIN") ||
                (userResponse.getRole().equals("RECRUITER") && Objects.equals(jobEntity.getRecruiterId(), userResponse.getId()))||
                (userResponse.getRole().equals("OWNER") && Objects.equals(jobEntity.getCompany().getOwnerId(),userResponse.getId()));
    }


}
