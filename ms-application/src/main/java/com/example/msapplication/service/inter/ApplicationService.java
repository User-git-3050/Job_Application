package com.example.msapplication.service.inter;

import com.example.msapplication.enums.StatusEnum;
import com.example.msapplication.dao.response.ApplicationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getAllApplications();

    ApplicationResponse getApplicationById(Long id);

    String createApplication(Long jobId, String username, MultipartFile resume);

    String updateApplicationStatus(StatusEnum status, Long id);

    String deleteApplication(Long id);

    String updateResume(Long applicationId, MultipartFile resume);
}
