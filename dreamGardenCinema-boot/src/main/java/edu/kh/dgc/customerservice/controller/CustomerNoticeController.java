package edu.kh.dgc.customerservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.dgc.customerservice.model.service.CustomerService;
import edu.kh.dgc.notice.model.dto.Notice;

@RestController
@RequestMapping("/customerservice")
public class CustomerNoticeController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping(value="/noticeSearchList",produces = "application/json; charset=UTF-8")
	public List<Notice> noticeSearchList(@RequestParam Map<String, Object> param){
		
		return service.noticeSearchList(param);
		
	}
	
	
	
	
	
	
	

}
