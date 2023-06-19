package edu.kh.dgc.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.customerservice.model.service.CustomerFAQService;

@Controller
@RequestMapping("/customerservice")
public class CustomerFAQController {
	
	@Autowired
	private CustomerFAQService FAQservice;
	
	// FAQ(상영관 이용 관련) 목록 조회
	@GetMapping(value="/theaterFAQ", produces = "application/json; charset=UTF-8")
	public String theaterList(FAQ faq) {
		
		FAQservice.theaterList(faq);
		
		return "customerservice/FAQ";
	}
	
	
	  
	
	

}
