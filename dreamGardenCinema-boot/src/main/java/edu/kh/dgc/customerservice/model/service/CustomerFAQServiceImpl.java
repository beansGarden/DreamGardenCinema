package edu.kh.dgc.customerservice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.customerservice.model.dao.CustomerServiceMapper;
import edu.kh.dgc.customerservice.model.dto.FAQ;

@Service
public class CustomerFAQServiceImpl implements CustomerFAQService {

	@Autowired
	private CustomerServiceMapper mapper;

	// FAQ(상영관 이용 관련) 목록 조회

	@Override
	public List<FAQ> theaterList(String fAQCategory) {
		return mapper.theaterList(fAQCategory);
	}
	
	



}
