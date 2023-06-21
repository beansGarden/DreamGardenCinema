package edu.kh.dgc.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.dgc.customerservice.model.service.CustomerService;

@RestController
@RequestMapping("/customerservice")
public class CustomerNoticeController {
	
	@Autowired
	private CustomerService service;
	
	
	
	
	
	

}
