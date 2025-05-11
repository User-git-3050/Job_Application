package az.msjobsearch.service.impl;

import az.msjobsearch.client.JobServiceClient;
import az.msjobsearch.dao.response.JobResponse;
import az.msjobsearch.dao.request.JobSearchCriteria;
import az.msjobsearch.service.JobSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSearchServiceImpl implements JobSearchService {

    private final JobServiceClient jobServiceClient;

    public JobSearchServiceImpl(JobServiceClient jobServiceClient) {
        this.jobServiceClient = jobServiceClient;
    }

    @Override
    public List<JobResponse> searchJobBasedOnCriteria(JobSearchCriteria jobSearchCriteria) {
        return jobServiceClient.searchJobsByCriteria(jobSearchCriteria).getBody();
    }

    @Override
    public List<JobResponse> searchJob(String keyword) {
        return jobServiceClient.searchJobs(keyword).getBody();
    }
}
