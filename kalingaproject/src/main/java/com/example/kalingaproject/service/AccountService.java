package com.example.kalingaproject.service;

import com.example.kalingaproject.dto.AccountDto;
import com.example.kalingaproject.exceptions.ServiceException;

public interface AccountService {

	public AccountDto addAccountProject(AccountDto accountDto) throws ServiceException;

}
