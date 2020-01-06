package com.example.kalingaproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kalingaproject.exceptions.ServiceException;

@RestControllerAdvice
public class ControllerHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Map<String,Object>> serviceExceptionHandler(Exception e)
	{
		Map<String,Object> response=new LinkedHashMap<String,Object>();
		response.put("header","kalinga-project");
		response.put("error", true);
		response.put("Body",e.getMessage());
		response.put("HttpStatus", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	}

}
