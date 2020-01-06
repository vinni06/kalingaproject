package com.example.kalingaproject.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.dto.IgDto;
import com.example.kalingaproject.dto.ProjectDto;
import com.example.kalingaproject.service.AccountService;
import com.example.kalingaproject.service.IgService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AppControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	IgService igServiceImpl;
	
	@MockBean
	AccountService accountServiceImplObj;


	@Before
	public void setup()
	{
	MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInsertAccountProject() throws Exception {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		ProjectDto project1 = new ProjectDto(1, "Mahesh", 123);
		ProjectDto project2 = new ProjectDto(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		AccountDto accountDto = new AccountDto(1,"mahesh", 500, projectList);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(accountDto);
        
        Mockito.when(
        		accountServiceImplObj.addAccountProject(
                        Mockito.any(AccountDto.class))).thenReturn(accountDto);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/insertAccountProject")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
   
            MockHttpServletResponse response = result.getResponse();
       
            assertEquals(HttpStatus.OK.value(), response.getStatus());
	
	}
	
	@Test
	public void testAddIg() throws Exception {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		ProjectDto project1 = new ProjectDto(1, "Mahesh", 123);
		ProjectDto project2 = new ProjectDto(2, "Nani", 123);
		projectList.add(project1);
		projectList.add(project2);
		AccountDto accountDto = new AccountDto(1,"mahesh", 500, projectList);
		List<AccountDto> savedAccounts=new ArrayList<AccountDto>();
		savedAccounts.add(accountDto);
		
		IgDto igDto=new IgDto(1,"ig1", savedAccounts);
		
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(igDto);
        
        
        
        
        Mockito.when(
        		igServiceImpl.addIg(
                        Mockito.any(IgDto.class))).thenReturn(igDto);
        
        
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/insertIg")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
   
            MockHttpServletResponse response = result.getResponse();
       
            assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	

	@Test
	public void testAssignIgToAccount() throws Exception {
		Mockito.when(igServiceImpl.assignIg(1,1)).thenReturn("saved");
		mockMvc.perform(post("/assign/1/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
	}

	@Test
	public void testSortByIg() throws Exception {
		Mockito.when(igServiceImpl.sortByIg(1)).thenReturn(Mockito.any(IgDto.class));	
		mockMvc.perform(get("/sort/1")).andExpect(status().isOk());
	}

}
