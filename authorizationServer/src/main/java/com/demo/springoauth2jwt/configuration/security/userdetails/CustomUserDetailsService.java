package com.demo.springoauth2jwt.configuration.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.springoauth2jwt.model.User;
import com.demo.springoauth2jwt.repository.UserRepository;
import com.demo.springoauth2jwt.repository.UserRolesRepository;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (null == user) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        List<String> userRoles = userRolesRepository.findRoleByUserName(userName);
        return new CustomUserDetails(user, userRoles);
    }
}
