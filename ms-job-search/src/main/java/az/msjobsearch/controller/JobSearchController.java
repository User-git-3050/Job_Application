package az.msjobsearch.controller;

import az.msjobsearch.dao.response.JobResponse;
import az.msjobsearch.dao.request.JobSearchCriteria;
import az.msjobsearch.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search/job/")
public class JobSearchController {

    private final JobSearchService jobSearchService;

    @Autowired
    public JobSearchController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    @GetMapping()
    public List<JobResponse> searchJob(@RequestParam String keyword) {
        return jobSearchService.searchJob(keyword);
    }

    @GetMapping("criteria")
    public List<JobResponse> searchJobsBasedOnCriteria(@RequestBody JobSearchCriteria jobSearchCriteria) {
        return jobSearchService.searchJobBasedOnCriteria(jobSearchCriteria);
    }
}
