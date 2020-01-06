package com.example.kalingaproject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonIgnoreProperties
//@JsonInclude(Include.NON_NULL)
public class IgDto {

	private int igId;
	private String igName;
	
	
	private List<AccountDto> account;


	public IgDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IgDto(int igId, String igName, List<AccountDto> account) {
		super();
		this.igId = igId;
		this.igName = igName;
		this.account = account;
	}


	public int getIgId() {
		return igId;
	}


	public void setIgId(int igId) {
		this.igId = igId;
	}


	public String getIgName() {
		return igName;
	}


	public void setIgName(String igName) {
		this.igName = igName;
	}


	public List<AccountDto> getAccount() {
		return account;
	}


	public void setAccount(List<AccountDto> account) {
		this.account = account;
	}

	
	
	
	
}
