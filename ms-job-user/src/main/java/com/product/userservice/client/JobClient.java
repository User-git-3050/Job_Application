package com.product.userservice.client;

import com.product.userservice.dto.response.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "MS-JOB"
)
public interface JobClient {
    @GetMapping("/api/v1/company/name/{companyName}")
    ResponseEntity<CompanyResponse> getCompanyByName(@PathVariable String companyName);

    @GetMapping("/api/v1/company/id/{id}")
    ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id);

}
