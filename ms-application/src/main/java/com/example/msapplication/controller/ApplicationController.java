package com.example.msapplication.controller;

import com.example.msapplication.dao.response.ApplicationResponse;
import com.example.msapplication.enums.StatusEnum;
import com.example.msapplication.service.inter.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/application/")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createApplication(@RequestParam Long jobId,
                                                    @RequestParam MultipartFile resume,
                                                    @RequestHeader String username) {
        return ResponseEntity.ok(applicationService.createApplication(jobId, username, resume));
    }

    @PutMapping(value = "{applicationId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> updateResume(@PathVariable Long applicationId,
                                               @RequestParam MultipartFile resume) {
        return ResponseEntity.ok(applicationService.updateResume(applicationId, resume));
    }

    @PutMapping("{id}/status")//by recruiter or admin
    public ResponseEntity<String> updateApplicationStatus(@RequestBody StatusEnum statusEnum, @PathVariable Long id) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(statusEnum, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.deleteApplication(id));
    }


}
