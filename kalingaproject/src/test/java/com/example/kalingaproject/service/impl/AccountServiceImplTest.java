package com.example.kalingaproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.dto.ProjectDto;
import com.example.kalingaproject.entity.Account;
import com.example.kalingaproject.entity.Project;
import com.example.kalingaproject.exceptions.ProjectCostMore;
import com.example.kalingaproject.exceptions.ServiceException;
import com.example.kalingaproject.repository.AccountRepository;
import com.example.kalingaproject.service.AccountService;


//@WebMvcTest(AccountServiceImpl.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
	
	@TestConfiguration
	public class AccountServiceImplTestConfiguration{
		@Bean
		public AccountService accountService()
		{
			return new AccountServiceImpl();
		}
		
	}
	
	
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	AccountRepository accountRepository; 
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	ModelMapper modelMapper= new ModelMapper();
	

	
	@Test
	public void testAddAccountProject() throws ServiceException {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		ProjectDto project1 = new ProjectDto(1, "Mahesh", 123);
		ProjectDto project2 = new ProjectDto(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		AccountDto accountDto = new AccountDto(1,"mahesh", 500, projectList);
		Account account = modelMapper.map(accountDto, Account.class);
		List<Project> savedProjectList = account.getProjects();
		int projectCount = 0;
		for (Project p : savedProjectList) {
			projectCount = projectCount + p.getCost();
		}
//			account.setProjects(savedProjectList);
			Mockito.when(accountRepository.save(account)).thenReturn(account);
			AccountDto finalaccountDto = modelMapper.map(account,AccountDto.class);
			//System.out.println(accountServiceImpl.hashCode());
			assertEquals(finalaccountDto.getAccountName(), accountServiceImpl.addAccountProject(accountDto).getAccountName());
	}
	
	@Test(expected=ProjectCostMore.class)
	public void testAddAccountProject2() throws ServiceException {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		ProjectDto project1 = new ProjectDto(1, "Mahesh", 123);
		ProjectDto project2 = new ProjectDto(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		AccountDto accountDto = new AccountDto(1,"mahesh", 0, projectList);
		Account account = modelMapper.map(accountDto, Account.class);
		List<Project> savedProjectList = account.getProjects();
		int projectCount = 0;
		for (Project p : savedProjectList) {
			projectCount = projectCount + p.getCost();
		}
//			account.setProjects(savedProjectList);
			Mockito.when(accountRepository.save(account)).thenReturn(account);
			AccountDto finalaccountDto = modelMapper.map(account,AccountDto.class);
			//System.out.println(accountServiceImpl.hashCode());
			assertEquals(finalaccountDto.getAccountName(), accountServiceImpl.addAccountProject(accountDto).getAccountName());
	}
	
	

}
