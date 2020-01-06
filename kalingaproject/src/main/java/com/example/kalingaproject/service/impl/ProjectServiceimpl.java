package com.example.kalingaproject.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.kalingaproject.repository.AccountRepository;
import com.example.kalingaproject.repository.IgRepository;
import com.example.kalingaproject.repository.ProjectRepository;
import com.example.kalingaproject.service.ProjectService;

@Service
public class ProjectServiceimpl implements ProjectService {

	
	@Autowired
	IgRepository igRepositoryObj;
	
	@Autowired
	ProjectRepository projectRepositoryObj;
	
	@Autowired
	AccountRepository accountRepositoryObj;
	ModelMapper mapper=new ModelMapper();


}
