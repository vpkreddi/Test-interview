package com.demo.springoauth2jwt.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.springoauth2jwt.domain.Company;
import com.demo.springoauth2jwt.domain.User;
import com.demo.springoauth2jwt.repository.CompanyRepository;
import com.demo.springoauth2jwt.repository.UserRepository;

@Component
public class CreateDefaultData implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

  
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	  logger.info("Creating user with role USER");
          User user = new User();
          user.setEmail("venkat@test.com");
          user.setEnabled(1);
          user.setUserName("venkat");
          user.setPassword("1234");
          userRepository.save(user);

       
          
          logger.info("Creating user with role USER");
          User user2 = new User();
          user2.setEmail("prasad@test.com");
          user2.setEnabled(1);
          user2.setUserName("prasad");
          user2.setPassword("1234");
          userRepository.save(user2);

         
          logger.info("Creating user with role USER");
          User user3 = new User();
          user3.setEmail("reddy@test.com");
          user3.setEnabled(1);
          user3.setUserName("reddy");
          user3.setPassword("1234");
          userRepository.save(user3);

        

          //Creating Admin
          logger.info("Creating user with role ADMIN");
          User user4 = new User();
          user4.setEmail("admin@test.com");
          user4.setEnabled(1);
          user4.setUserName("admin");
          user4.setPassword("4321");
          userRepository.save(user4);

        
        
        logger.info("creating companies of the users");
        
        Company cmp = new Company();
        cmp.setUserId("1");
        cmp.setName("company1");
        cmp.setDesignation("employee");
        companyRepository.save(cmp);
        
        logger.info("creating companies of the users");
        Company cmp2 = new Company();
        cmp2.setUserId("2");
        cmp2.setName("company2");
        cmp2.setDesignation("employee");
        companyRepository.save(cmp2);
        
        logger.info("creating companies of the users");
        
        Company cmp3 = new Company();
        cmp3.setUserId("3");
        cmp3.setName("company3");
        cmp3.setDesignation("employee");
        companyRepository.save(cmp3);
        logger.info("creating companies of the users");
        
        Company cmp4 = new Company();
        cmp4.setUserId("1");
        cmp4.setName("company4");
        cmp4.setDesignation("employee");
        companyRepository.save(cmp4);
        logger.info("creating companies of the users");
        
        Company cmp5 = new Company();
        cmp5.setUserId("2");
        cmp5.setName("company5");
        cmp5.setDesignation("employee");
        companyRepository.save(cmp5);
        logger.info("creating companies of the users");
        
        Company cmp6 = new Company();
        cmp6.setUserId("3");
        cmp6.setName("company6");
        cmp6.setDesignation("employee");
        companyRepository.save(cmp6);
        
        
      
    }
}
