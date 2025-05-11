package az.msjobsearch.service;

import az.msjobsearch.dao.response.JobResponse;
import az.msjobsearch.dao.request.JobSearchCriteria;

import java.util.List;

public interface JobSearchService {
    List<JobResponse> searchJobBasedOnCriteria(JobSearchCriteria jobSearchCriteria);

    List<JobResponse> searchJob(String keyword);
}
