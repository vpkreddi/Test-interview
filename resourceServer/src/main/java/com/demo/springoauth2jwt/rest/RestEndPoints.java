package com.demo.springoauth2jwt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springoauth2jwt.domain.Company;
import com.demo.springoauth2jwt.domain.CompanyUserDetails;
import com.demo.springoauth2jwt.domain.User;
import com.demo.springoauth2jwt.repository.CompanyRepository;
import com.demo.springoauth2jwt.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RestEndPoints {
    @Autowired
    private ResourceServerTokenServices tokenServices;
    
    @Autowired
    private UserRepository userrepository;
    
    @Autowired
    private CompanyRepository companyrepository;
    
    

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<CompanyUserDetails> endPointUser(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> additionalInfo = tokenServices.readAccessToken(oAuth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();
        
        User user = userrepository.findById((String)additionalInfo.get("uuid"));
        
        CompanyUserDetails cud = new CompanyUserDetails();
        cud.setUsername(user.getUserName());
        
        List<Company> companies = companyrepository.findByUserId(user.getId());
        
        cud.setCompanies(companies);
        
        return  new ResponseEntity(cud , HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyUserDetails>> endPointAdmin(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> additionalInfo = tokenServices.readAccessToken(oAuth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();
        List<User> users = userrepository.findAll();
        
        List<CompanyUserDetails> companyuserdetailsforadmin = new ArrayList<CompanyUserDetails>(0);
        
        for (User user: users) {
        	 CompanyUserDetails cud = new CompanyUserDetails();
             cud.setUsername(user.getUserName());
             List<Company> companies = companyrepository.findByUserId(user.getId());
           cud.setCompanies(companies);
           companyuserdetailsforadmin.add(cud);
        	
        }
       
      
        return new ResponseEntity<List<CompanyUserDetails>>(companyuserdetailsforadmin,HttpStatus.OK);
    }
}
