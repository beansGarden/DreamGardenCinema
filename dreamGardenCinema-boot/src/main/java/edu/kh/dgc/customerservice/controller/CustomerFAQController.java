package edu.kh.dgc.customerservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.customerservice.model.service.CustomerFAQService;


@RestController
@RequestMapping("/customerservice")
public class CustomerFAQController {
	
	@Autowired
	private CustomerFAQService FAQservice;
	
	// FAQ(상영관 이용 관련) 목록 조회
	@GetMapping(value="/theaterFAQ", produces = "application/json; charset=UTF-8")
	public List<FAQ> theaterList(String FAQCategory){
		
		return FAQservice.theaterList(FAQCategory);
		
	}
//	public String theaterList(FAQ FAQcategory) {
//		List<FAQ> theaterList = FAQservice.theaterList(FAQcategory);
		
		
	}
	
	
	  
	
	


