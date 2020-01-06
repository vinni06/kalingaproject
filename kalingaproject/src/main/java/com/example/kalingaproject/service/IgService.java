package com.example.kalingaproject.service;

import com.example.kalingaproject.dto.IgDto;
import com.example.kalingaproject.exceptions.ServiceException;

public interface IgService {

	public IgDto addIg(IgDto igDto);

	public Object assignIg(int igId, int accountId) throws ServiceException;

	public IgDto sortByIg(int igId);


}
