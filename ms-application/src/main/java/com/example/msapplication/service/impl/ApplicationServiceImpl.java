package com.example.msapplication.service.impl;

import com.example.msapplication.client.JobClient;
import com.example.msapplication.dao.response.ApplicationResponse;
import com.example.msapplication.entity.ApplicationEntity;
import com.example.msapplication.enums.ErrorMessages;
import com.example.msapplication.enums.InfoMessages;
import com.example.msapplication.enums.StatusEnum;
import com.example.msapplication.event.ApplicationEvent;
import com.example.msapplication.event.JobApplicationEvent;
import com.example.msapplication.mapper.ApplicationMapper;
import com.example.msapplication.service.inter.ApplicationService;
import com.example.msapplication.client.UserClient;
import com.example.msapplication.dao.response.UserResponse;
import com.example.msapplication.exception.BadRequestException;
import com.example.msapplication.exception.NotFoundException;
import com.example.msapplication.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.msapplication.enums.ErrorMessages.RESUME_FILE_IS_MISSING;
import static java.lang.String.format;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobClient jobClient;
    private final UserClient userClient;
    private final FileServiceImpl fileServiceImpl;
    private final KafkaTemplate<String, Object> increasingApplicants;


    private final KafkaTemplate<String, Object> applicationKafkaEvent;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobClient jobClient, UserClient userClient
            , FileServiceImpl fileServiceImpl, KafkaTemplate<String, Object> increasingApplicants, KafkaTemplate<String, Object> applicationKafkaEvent) {
        this.applicationRepository = applicationRepository;
        this.jobClient = jobClient;
        this.userClient = userClient;
        this.fileServiceImpl = fileServiceImpl;
        this.increasingApplicants = increasingApplicants;
        this.applicationKafkaEvent = applicationKafkaEvent;
    }


    @Override
    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll().stream().map(ApplicationMapper.APPLICATION_MAPPER::toResponse).toList();
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        return applicationRepository.findById(id).map(ApplicationMapper.APPLICATION_MAPPER::toResponse)
                .orElseThrow(() -> new NotFoundException(
                        String.format(
                                ErrorMessages.APPLICATION_NOT_FOUND.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public String createApplication(Long jobId, String username, MultipartFile resume) {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();
        jobClient.existsJobById(jobId).getBody();


        if (resume.isEmpty()) {
            throw new BadRequestException(RESUME_FILE_IS_MISSING.getMessage());
        }


        String resumePath = fileServiceImpl.saveFile(resume);

        applicationRepository.save(ApplicationMapper.APPLICATION_MAPPER.toEntity(jobId, userResponse.getId(), resumePath));


        increasingApplicants.send("job-application-topic", JobApplicationEvent.builder().jobId(jobId).build());


        return InfoMessages.APPLICATION_CREATED.getMessage();
    }

    @Override
    @Transactional
    public String updateApplicationStatus(StatusEnum status, Long id) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(
                                ErrorMessages.APPLICATION_NOT_FOUND.getMessage(),
                                id
                        )
                ));
        applicationEntity.setStatus(status);
        applicationRepository.save(applicationEntity);

        if (status.equals(StatusEnum.ACCEPTED)) {
            Long userId = applicationEntity.getUserId();
            UserResponse userResponse = userClient.findUserById(userId).getBody();
            applicationKafkaEvent.send("application-topic", ApplicationEvent.builder() //for notification
                    .applicantId(userId)
                    .applicantName(userResponse.getName())
                    .applicantMail(userResponse.getEmail())
                    .status(status)
                    .build());
        }

        return InfoMessages.APPLICATION_STATUS_UPDATED.getMessage();
    }

    @Override
    public String deleteApplication(Long id) {
        applicationRepository.deleteById(id);
        return InfoMessages.APPLICATION_DELETED.getMessage();
    }

    @Override
    public String updateResume(Long applicationId, MultipartFile resume) {
        ApplicationEntity applicationEntity = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException(
                        String.format(
                                ErrorMessages.APPLICATION_NOT_FOUND.getMessage(),
                                applicationId
                        )
                ));

        if (resume == null || resume.isEmpty()) {
            throw new BadRequestException(RESUME_FILE_IS_MISSING.getMessage());
        }

        String newResumePath = fileServiceImpl.saveFile(resume);
        applicationEntity.setResumePath(newResumePath);

        applicationRepository.save(applicationEntity);
        return InfoMessages.RESUME_UPDATED.getMessage();
    }


}
