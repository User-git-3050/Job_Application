package az.msjobsearch.controller;


import az.msjobsearch.dao.response.UserSearchResponse;
import az.msjobsearch.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search/user/")
public class UserSearchController {
    private final UserSearchService userSearchService;

    @Autowired
    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping
    public List<UserSearchResponse> searchUser(@RequestParam String keyword){
        return userSearchService.searchUser(keyword);
    }

}
