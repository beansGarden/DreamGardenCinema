package edu.kh.dgc.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.customerservice.model.service.CustomerService;

@RequestMapping("/customerservice")
@Controller
public class CustomerServiceController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/")
	public String FAQ() {
		
		return "customerservice/FAQ";
	}
	
	
}
