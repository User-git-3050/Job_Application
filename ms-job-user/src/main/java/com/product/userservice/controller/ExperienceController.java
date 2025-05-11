package com.product.userservice.controller;

import com.product.userservice.dto.request.ExperienceRequest;
import com.product.userservice.dto.response.ExperienceResponse;
import com.product.userservice.service.inter.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/experience/")
public class ExperienceController {
    private final ExperienceService experienceService;

    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    @GetMapping("{id}")
    public ResponseEntity<ExperienceResponse> getExperienceById(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    @PostMapping
    public ResponseEntity<String> createExperience(@RequestBody ExperienceRequest experienceRequest,@RequestHeader String username){
        return ResponseEntity.ok(experienceService.createExperience(experienceRequest,username));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateExperience(@PathVariable Long id, @RequestBody ExperienceRequest experienceRequest,@RequestHeader String username){
        return ResponseEntity.ok(experienceService.updateExperience(id,experienceRequest,username));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExperienceById(@PathVariable Long id,@RequestHeader String username) {
        return ResponseEntity.ok(experienceService.deleteExperienceById(id,username));
    }

}
