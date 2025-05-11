package com.example.msapplication.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "MS-JOB"
)
public interface JobClient {
    @GetMapping("api/v1/job/exists/{id}")
    ResponseEntity<Boolean> existsJobById(@PathVariable Long id);


}
