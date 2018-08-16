package com.demo.springoauth2jwt.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springoauth2jwt.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUserName(String username);
    
    User findById(String id);
    
    List<User> findAll();
}
