package az.msjobsearch.client;

import az.msjobsearch.dao.response.JobResponse;
import az.msjobsearch.dao.request.JobSearchCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MS-JOB")
public interface JobServiceClient {

    @GetMapping("api/v1/job/search")
    ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword);

    @PostMapping("api/v1/job/search/criteria")
    ResponseEntity<List<JobResponse>> searchJobsByCriteria(@RequestBody JobSearchCriteria jobSearchCriteria);
}
