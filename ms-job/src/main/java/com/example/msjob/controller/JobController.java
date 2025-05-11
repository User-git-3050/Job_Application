package com.example.msjob.controller;

import com.example.msjob.dao.request.JobRequest;
import com.example.msjob.dao.request.JobSearchCriteria;
import com.example.msjob.dao.response.JobResponse;
import com.example.msjob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job/")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("exists/{id}")
    public ResponseEntity<Boolean> existsJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.existsJobById(id));
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobRequest jobRequest, @RequestHeader String username) {
        return ResponseEntity.ok(jobService.createJob(jobRequest, username));

    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody JobRequest jobRequest, @RequestHeader String username) {
        return ResponseEntity.ok(jobService.updateJobById(id, jobRequest, username));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id, @RequestHeader String username) {
        return ResponseEntity.ok(jobService.deleteJobById(id, username));
    }

    @GetMapping("search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword) {
        return ResponseEntity.ok(jobService.searchJobs(keyword));
    }

    @PostMapping("search/criteria")
    public ResponseEntity<List<JobResponse>> searchJobsByCriteria(@RequestBody JobSearchCriteria jobSearchCriteria) {
        return ResponseEntity.ok(jobService.searchJobsByCriteria(jobSearchCriteria));
    }


}
