package az.msjobsearch.service;

import az.msjobsearch.dao.response.UserSearchResponse;

import java.util.List;

public interface UserSearchService {
    List<UserSearchResponse> searchUser(String keyword);
}
