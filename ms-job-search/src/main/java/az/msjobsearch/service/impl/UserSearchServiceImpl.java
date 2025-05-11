package az.msjobsearch.service.impl;

import az.msjobsearch.client.UserServiceClient;
import az.msjobsearch.dao.response.UserSearchResponse;
import az.msjobsearch.service.UserSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    private final UserServiceClient userServiceClient;

    public UserSearchServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public List<UserSearchResponse> searchUser(String keyword) {
        return userServiceClient.searchUsers(keyword).getBody();
    }

}
