package az.msjobauth.service.impl;

import az.msjobauth.client.UserClient;
import az.msjobauth.dao.CustomUserDetails;
import az.msjobauth.dao.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserClient userClient;

    @Autowired
    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();
        if (userResponse==null){
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(userResponse);
    }
}
