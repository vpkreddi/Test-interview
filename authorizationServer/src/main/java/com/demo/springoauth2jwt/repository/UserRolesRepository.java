package com.demo.springoauth2jwt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springoauth2jwt.model.UserRole;

import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, String> {
    @Query("select userrole.role from UserRole userrole, User user where user.userName=?1 and userrole.userId=user.id")
    List<String> findRoleByUserName(String username);
}
