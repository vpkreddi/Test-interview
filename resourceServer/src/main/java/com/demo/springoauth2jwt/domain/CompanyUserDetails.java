package com.demo.springoauth2jwt.domain;

import java.util.List;

public class CompanyUserDetails {
	
	private String username;
	
	private List<Company> companies;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

}
