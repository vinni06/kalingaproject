package com.example.kalingaproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.dto.IgDto;
import com.example.kalingaproject.dto.ProjectDto;
import com.example.kalingaproject.entity.Account;
import com.example.kalingaproject.entity.Ig;
import com.example.kalingaproject.entity.Project;
import com.example.kalingaproject.exceptions.NoIgFound;
import com.example.kalingaproject.exceptions.ServiceException;
import com.example.kalingaproject.repository.AccountRepository;
import com.example.kalingaproject.repository.IgRepository;
import com.example.kalingaproject.repository.ProjectRepository;
import com.example.kalingaproject.service.IgService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IgServiceImplTest {

	@TestConfiguration
	public class IgServiceImplTestConfg{
		
		@Bean
		public IgService igService(){
			return new IgServiceImpl() ;
			
		}
		
	}
	
	@MockBean
	IgRepository igRepositoryObj;

	@MockBean
	ProjectRepository projectRepositoryObj;

	@MockBean
	AccountRepository accountRepositoryObj;
	
	
//	@InjectMocks
	@Autowired
	IgServiceImpl igserviceImplObj;
	
	ModelMapper mapper=new ModelMapper();
	
	@Test
	public void testAddIg() {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		ProjectDto project1 = new ProjectDto(1, "vinni", 123);
		ProjectDto project2 = new ProjectDto(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		AccountDto accountDto = new AccountDto(1,"smiley", 500, projectList);
		List<AccountDto> savedAccounts=new ArrayList<AccountDto>();
		savedAccounts.add(accountDto);
		
		IgDto igDto=new IgDto(1,"ig1", savedAccounts);		
		Ig ig = mapper.map(igDto, Ig.class);
		
		Mockito.when(igRepositoryObj.save(Mockito.any(Ig.class))).thenReturn(ig);
		IgDto savedDto=mapper.map(ig, IgDto.class);
		assertEquals(savedDto.getIgId(),igserviceImplObj.addIg(igDto).getIgId());
	}
	
	
	@Test
	public void testassignIg() throws ServiceException
	
	{
		List<Project> projectList = new ArrayList<Project>();
		Project project1 = new Project(1, "vinni", 123);
		Project project2 = new Project(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		Account account = new Account(1,"vinni", 500, projectList);
		List<Account> savedAccounts=new ArrayList<Account>();
		savedAccounts.add(account);
		
		Ig igs=new Ig(1,"ig1", savedAccounts);
		int igId=1;
		int accountId=1;

        Mockito.when(igRepositoryObj.getOne(igId)).thenReturn(igs);
	    Mockito.when(accountRepositoryObj.getOne(accountId)).thenReturn(account);
		

		igs.getAccount().add(account);
		Mockito.when(igRepositoryObj.save(igs)).thenReturn(igs);
		assertEquals("saved",igserviceImplObj.assignIg(igId, accountId));
		
		
	}
	@Test(expected = NoIgFound.class)
	public void testassignIg1() throws ServiceException
	
	{
		List<Project> projectList = new ArrayList<Project>();
		Project project1 = new Project(1, "vinni", 123);
		Project project2 = new Project(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		Account account = new Account(1,"vinni", 500, projectList);
		List<Account> savedAccounts=new ArrayList<Account>();
		savedAccounts.add(account);
		
		Ig igs=new Ig(1,"ig1", savedAccounts);
		int igId=1;
		int accountId=1;

        Mockito.when(igRepositoryObj.getOne(igId)).thenReturn(igs);
	    Mockito.when(accountRepositoryObj.getOne(accountId)).thenReturn(account);
		

		igs.getAccount().add(account);
		Mockito.when(igRepositoryObj.save(igs)).thenReturn(igs);
		assertEquals("saved",igserviceImplObj.assignIg(2, accountId));
		
		
	}
	
	@Test
	public void sortByIgTest()
	{
		int igId=1;
		List<Project> projectList = new ArrayList<Project>();
		Project project1 = new Project(1, "vinni", 123);
		Project project2 = new Project(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		Account account = new Account(1,"vinni", 500, projectList);
		List<Account> savedAccounts=new ArrayList<Account>();
		savedAccounts.add(account);
		
		Ig igs=new Ig(1,"ig1", savedAccounts);
		Mockito.when(igRepositoryObj.getOne(igId)).thenReturn(igs);
		
	
		Collections.sort(igs.getAccount(), Collections.reverseOrder());
		
		IgDto savedIgDto=mapper.map(igs,IgDto.class);
		
		assertEquals(savedIgDto.getIgName(),igserviceImplObj.sortByIg(igId).getIgName());

		
	}
	

	 

}
