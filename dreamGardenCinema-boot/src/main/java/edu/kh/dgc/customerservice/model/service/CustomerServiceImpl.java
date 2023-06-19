package edu.kh.dgc.customerservice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.customerservice.model.dao.CustomerServiceMapper;
import edu.kh.dgc.customerservice.model.dto.FAQ;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerServiceMapper mapper;
	

	@Override
	public List<FAQ> main(FAQ faq) {
		return mapper.main(faq);
	}

	
}
