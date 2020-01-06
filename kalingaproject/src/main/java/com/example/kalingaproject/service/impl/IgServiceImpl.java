package com.example.kalingaproject.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.dto.IgDto;
import com.example.kalingaproject.entity.Account;
import com.example.kalingaproject.entity.Ig;
import com.example.kalingaproject.entity.Project;
import com.example.kalingaproject.exceptions.NoIgFound;
import com.example.kalingaproject.exceptions.NoSuchAccountId;
import com.example.kalingaproject.exceptions.ProjectCostMore;
import com.example.kalingaproject.exceptions.ServiceException;
import com.example.kalingaproject.repository.AccountRepository;
import com.example.kalingaproject.repository.IgRepository;
import com.example.kalingaproject.repository.ProjectRepository;
import com.example.kalingaproject.service.IgService;

@Service
public class IgServiceImpl implements IgService {

	@Autowired
	IgRepository igRepositoryObj;

	@Autowired
	ProjectRepository projectRepositoryObj;

	@Autowired
	AccountRepository accountRepositoryObj;

	ModelMapper mapper = new ModelMapper();

	@Override
	public IgDto addIg(IgDto igDto) {
		Ig ig = mapper.map(igDto, Ig.class);
		Ig savedIgs = igRepositoryObj.save(ig);
		IgDto savedIgDto=mapper.map(savedIgs, IgDto.class);
		return savedIgDto;

	}

	@Override
	public Object assignIg(int igId, int accountId) throws ServiceException {

//		Optional<Ig> igs = igRepositoryObj.findById(igId);
		Ig igs = igRepositoryObj.getOne(igId);
		

//		try {
//			
//			igs.orElseThrow(() -> new NoIgFound("no such Ig found"));
//		} catch (NoIgFound e) {
//			throw new ServiceException(e.getMessage());
//		}
		
		if(igs==null) {
			throw new NoIgFound("no such Ig found");
		}
		
		Account account=accountRepositoryObj.getOne(accountId);
//		igs.get().getAccount().add(account);
//		igRepositoryObj.save(igs.get());
		igs.getAccount().add(account);
		igRepositoryObj.save(igs);
		return "saved";
	}

	@Override
	public IgDto sortByIg(int igId) {

		Ig igs = igRepositoryObj.getOne(igId);
		System.out.println(igs.getAccount().size());
		Collections.sort(igs.getAccount(), Collections.reverseOrder());
		
		IgDto savedIgDto=mapper.map(igs, IgDto.class);

		return savedIgDto;
	}

}
