package edu.kh.dgc.customerservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.customerservice.model.service.CustomerFAQService;

@Controller
@RequestMapping("/customerservice")
public class CustomerFAQController {
	
	@Autowired
	private CustomerFAQService service;
//	
//	@GetMapping("/")
//	public  
	
	

}
