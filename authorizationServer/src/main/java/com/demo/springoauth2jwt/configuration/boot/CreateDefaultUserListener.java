package com.demo.springoauth2jwt.configuration.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.springoauth2jwt.model.User;
import com.demo.springoauth2jwt.model.UserRole;
import com.demo.springoauth2jwt.repository.UserRepository;
import com.demo.springoauth2jwt.repository.UserRolesRepository;

@Component
public class CreateDefaultUserListener implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Creating User
        logger.info("Creating user with role USER");
        User user = new User();
        user.setEmail("venkat@test.com");
        user.setEnabled(1);
        user.setUserName("venkat");
        user.setPassword("1234");
        userRepository.save(user);

        UserRole role = new UserRole();
        role.setRole("ROLE_USER");
        role.setUserId(user.getId());
        userRolesRepository.save(role);
        
        logger.info("Creating user with role USER");
        User user2 = new User();
        user2.setEmail("prasad@test.com");
        user2.setEnabled(1);
        user2.setUserName("prasad");
        user2.setPassword("1234");
        userRepository.save(user2);

        UserRole role2 = new UserRole();
        role2.setRole("ROLE_USER");
        role2.setUserId(user2.getId());
        userRolesRepository.save(role2);
        
        logger.info("Creating user with role USER");
        User user3 = new User();
        user3.setEmail("reddy@test.com");
        user3.setEnabled(1);
        user3.setUserName("reddy");
        user3.setPassword("1234");
        userRepository.save(user3);

        UserRole role3 = new UserRole();
        role3.setRole("ROLE_USER");
        role3.setUserId(user3.getId());
        userRolesRepository.save(role3);

        //Creating Admin
        logger.info("Creating user with role ADMIN");
        User user4 = new User();
        user4.setEmail("admin@test.com");
        user4.setEnabled(1);
        user4.setUserName("admin");
        user4.setPassword("4321");
        userRepository.save(user4);

        UserRole role4 = new UserRole();
        role4.setRole("ROLE_ADMIN");
        role4.setUserId(user4.getId());
        userRolesRepository.save(role4);
    }
}
