package edu.kh.dgc.customerservice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.customerservice.model.dao.CustomerServiceMapper;

@Service
public class CustomerFAQServiceImpl implements CustomerFAQService {

	@Autowired
	private CustomerServiceMapper mapper;
	
	



}
