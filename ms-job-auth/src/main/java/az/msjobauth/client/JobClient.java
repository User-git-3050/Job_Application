package az.msjobauth.client;

import az.msjobauth.dao.CompanyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MS-JOB")
public interface JobClient {
    @PostMapping("api/v1/company/")
    ResponseEntity<String> createCompany(@RequestBody CompanyRequest companyRequest, @RequestParam Long ownerId);
}
