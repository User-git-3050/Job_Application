package com.example.msjob.service;

import com.example.msjob.dao.request.JobRequest;
import com.example.msjob.dao.request.JobSearchCriteria;
import com.example.msjob.dao.response.JobResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<JobResponse> getAllJobs();

    String createJob(JobRequest jobRequest,String username);

    String updateJobById(Long id, JobRequest jobRequest,String username);

    JobResponse getJobById(Long id);

    String deleteJobById(Long id,String username);

    List<JobResponse> searchJobs(String keyword);

    Boolean existsJobById(Long id);

    List<JobResponse> searchJobsByCriteria(JobSearchCriteria jobSearchCriteria);
}
