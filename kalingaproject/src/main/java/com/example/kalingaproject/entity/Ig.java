package com.example.kalingaproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int igId;
	private String igName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> account;
	public Ig() {
		super();
	}
	public Ig(int igId, String igName, List<Account> account) {
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
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Ig [igId=" + igId + ", igName=" + igName + ", account=" + account + "]";
	}
	
	
	
	
	

}
