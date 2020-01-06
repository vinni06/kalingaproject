package com.example.kalingaproject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class AccountDto {
	
	private int accountId;
	private String accountName;
	private int revenue;
	
	private List<ProjectDto> projects;

	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDto(int accountId, String accountName, int revenue, List<ProjectDto> projects) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.revenue = revenue;
		this.projects = projects;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public List<ProjectDto> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
	}

	
	
	
	
	
	

}
