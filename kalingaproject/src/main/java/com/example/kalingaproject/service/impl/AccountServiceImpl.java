package com.example.kalingaproject.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.entity.Account;
import com.example.kalingaproject.entity.Project;
import com.example.kalingaproject.exceptions.ProjectCostMore;
import com.example.kalingaproject.exceptions.ServiceException;
import com.example.kalingaproject.repository.AccountRepository;
import com.example.kalingaproject.repository.IgRepository;
import com.example.kalingaproject.repository.ProjectRepository;
import com.example.kalingaproject.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	IgRepository igRepositoryObj;
	
	@Autowired
	ProjectRepository projectRepositoryObj;
	
	@Autowired
	AccountRepository accountRepositoryObj;
	
	ModelMapper mapper =new ModelMapper();
	@Override
	public AccountDto addAccountProject(AccountDto accountDto) throws ServiceException {
		
		
		
		Account account = mapper.map(accountDto, Account.class);
		System.out.println(account);
		List<Project> projectList = account.getProjects();
		int projectCount = 0;
		for (Project p : projectList) {
			projectCount = projectCount + p.getCost();
		}
		if (projectCount > account.getRevenue()) {
			throw new ProjectCostMore("project cost is more than account revenue");

		} else {
//			account.setProjects(projectList);
			Account savedAccounts = accountRepositoryObj.save(account);
			AccountDto sol= mapper.map(savedAccounts, AccountDto.class);
			return sol;
		}

	
	}

}
