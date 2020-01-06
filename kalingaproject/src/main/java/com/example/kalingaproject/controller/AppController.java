package com.example.kalingaproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.dto.IgDto;
import com.example.kalingaproject.exceptions.ServiceException;
import com.example.kalingaproject.service.AccountService;
import com.example.kalingaproject.service.IgService;
import com.example.kalingaproject.service.ProjectService;

@RestController
public class AppController {

	@Autowired
	IgService igServiceImpl;

	@Autowired
	AccountService accountServiceImplObj;

	@Autowired
	ProjectService projectServiceImpl;
	
	@RequestMapping("/insertIg")
	public ResponseEntity<Map<String, Object>> addIg(@RequestBody IgDto igDto) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("header", "kalinga-project");
		response.put("error", false);
		response.put("Body", igServiceImpl.addIg(igDto));
		response.put("HttpStatus", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/insertAccountProject")
	public ResponseEntity<Map<String, Object>> insertAccountProject(@RequestBody AccountDto accountDto)
			throws ServiceException {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("header", "kalinga-project");
		response.put("error", false);
		response.put("Body", accountServiceImplObj.addAccountProject(accountDto));
		response.put("HttpStatus", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/assign/{igId}/{accountId}")
	public ResponseEntity<Map<String, Object>> assignIgToAccount(@PathVariable int igId, @PathVariable int accountId) throws ServiceException {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("header", "kalinga-project");
		response.put("error", false);

		response.put("Body", igServiceImpl.assignIg(igId,accountId));

		response.put("HttpStatus", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/sort/{igId}")
	public ResponseEntity<Map<String, Object>> sortByIg(@PathVariable int igId) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("header", "kalinga-project");
		response.put("error", false);
		response.put("Body", igServiceImpl.sortByIg(igId));
		response.put("HttpStatus", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
