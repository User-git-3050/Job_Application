package com.product.userservice.service.impl;

import com.product.userservice.client.JobClient;
import com.product.userservice.dto.request.ExperienceRequest;
import com.product.userservice.dto.response.CompanyResponse;
import com.product.userservice.dto.response.ExperienceResponse;
import com.product.userservice.entity.ExperienceEntity;
import com.product.userservice.entity.UserEntity;
import com.product.userservice.entity.UserProfile;
import com.product.userservice.exception.NotFoundException;
import com.product.userservice.exception.UnauthorizedException;
import com.product.userservice.repository.ExperienceRepository;
import com.product.userservice.repository.UserRepository;
import com.product.userservice.service.inter.ExperienceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.product.userservice.enums.ErrorMessage.*;
import static com.product.userservice.enums.InfoMessage.*;
import static com.product.userservice.mapper.ExperienceMapper.*;
import static java.lang.String.format;

@Service
public class ExperienceServiceImpl implements ExperienceService {


    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final JobClient jobClient;

    @Autowired
    public ExperienceServiceImpl(ExperienceRepository experienceRepository, UserRepository userRepository, JobClient jobClient) {
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
        this.jobClient = jobClient;
    }

    @Override
    public List<ExperienceResponse> getAllExperiences() {
        return experienceRepository.findAll().stream().map(EXPERIENCE_MAPPER::mapToResponse).toList();
    }

    @Override
    public ExperienceResponse getExperienceById(Long id) {
        return EXPERIENCE_MAPPER.mapToResponse(getExperienceEntityById(id));

    }

    @Override
    public String createExperience(ExperienceRequest experienceRequest, String username) {
        CompanyResponse companyResponse = getCompanyById(experienceRequest.getCompanyId());

        UserEntity userEntity = getUserByUsername(username);
        UserProfile userProfile = userEntity.getUserProfile();

        experienceRepository.save(EXPERIENCE_MAPPER.mapToEntity(experienceRequest, userProfile));

        return EXPERIENCE_ADDED_TO_USER.getMessage();
    }

    @Override
    public String updateExperience(Long id, ExperienceRequest experienceRequest, String username) {
        CompanyResponse companyResponse = getCompanyById(experienceRequest.getCompanyId());

        UserEntity userEntity = getUserByUsername(username);

        ExperienceEntity experienceEntity = getExperienceEntityById(id);

        if (hasPermission(experienceEntity, userEntity)) {
            experienceEntity.setCompanyId(experienceRequest.getCompanyId());
            experienceEntity.setDescription(experienceRequest.getDescription());
            experienceEntity.setJobName(experienceRequest.getJobName());
            experienceEntity.setStartDate(experienceRequest.getStartDate());
            experienceEntity.setEndDate(experienceRequest.getEndDate());
            experienceEntity.setEmploymentType(experienceRequest.getEmploymentType());
            experienceEntity.setLocationType(experienceRequest.getLocationType());
            experienceEntity.setStartDate(experienceRequest.getStartDate());
            experienceEntity.setEndDate(experienceRequest.getEndDate());

            experienceRepository.save(experienceEntity);

            return EXPERIENCE_UPDATED.getMessage();
        }

        throw new UnauthorizedException(NO_PERMISSION.getMessage());


    }

    @Override
    public String deleteExperienceById(Long id, String username) {
        UserEntity userEntity = getUserByUsername(username);

        ExperienceEntity experienceEntity = getExperienceEntityById(id);

        if (!hasPermission(experienceEntity,userEntity)) {
            throw new UnauthorizedException(NO_PERMISSION.getMessage());
        }
        userEntity.getUserProfile().getExperiences().remove(experienceEntity);

        experienceRepository.deleteById(id);

        return EXPERIENCE_DELETED.getMessage();

    }


    private Boolean hasPermission(ExperienceEntity experienceEntity, UserEntity userEntity) {
            return userEntity.getUserProfile().getExperiences().contains(experienceEntity);
    }

    private CompanyResponse getCompanyById(Long companyId) {
        return jobClient.getCompanyById(companyId).getBody();
    }

    private UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_NAME.getMessage(),
                                username
                        )
                ));
    }

    private ExperienceEntity getExperienceEntityById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                EXPERIENCE_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));
    }


}
