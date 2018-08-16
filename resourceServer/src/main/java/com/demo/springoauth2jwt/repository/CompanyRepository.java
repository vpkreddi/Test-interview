package com.demo.springoauth2jwt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springoauth2jwt.domain.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {
	
	Company findById(String id);
	
	List<Company> findByUserId(String userid);
   
}
