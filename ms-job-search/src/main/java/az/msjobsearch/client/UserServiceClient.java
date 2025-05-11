package az.msjobsearch.client;

import az.msjobsearch.dao.response.UserSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MS-USER")
public interface UserServiceClient {
    @GetMapping("api/v1/user/search")
    ResponseEntity<List<UserSearchResponse>> searchUsers(@RequestParam String keyword);

}
