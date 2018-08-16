package com.demo.springoauth2jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springoauth2jwt.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUserName(String username);
}
