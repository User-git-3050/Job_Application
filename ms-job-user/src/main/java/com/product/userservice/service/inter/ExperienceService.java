package com.product.userservice.service.inter;

import com.product.userservice.dto.request.ExperienceRequest;
import com.product.userservice.dto.response.ExperienceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExperienceService {
    List<ExperienceResponse> getAllExperiences();

    ExperienceResponse getExperienceById(Long id);

    String createExperience(ExperienceRequest experienceRequest,String username);

    String updateExperience(Long id, ExperienceRequest experienceRequest,String username);

    String deleteExperienceById(Long id,String username);
}
