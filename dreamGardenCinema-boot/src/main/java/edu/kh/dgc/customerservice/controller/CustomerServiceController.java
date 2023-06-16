package edu.kh.dgc.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.customerservice.model.service.CustomerService;

@RequestMapping("/customerservice")
@Controller
public class CustomerServiceController {

	@Autowired
	private CustomerService service;
	
	
	// 고객센터 메인 페이지(FAQ 화면)
	@GetMapping("/")
	public String Main(Model model, FAQ faq) {
		
		List<FAQ> FAQList = service.Main(faq);
		
		model.addAttribute("FAQList", FAQList);
		
		return "customerservice/FAQ";
	}
	
	// 멤버십 이용 약관 페이지
	@GetMapping("/terms")
	public String terms() {
		
		return "customerservice/membership-info";
	}
	
	@GetMapping("/FAQ")
	public String FAQ() {
		
		return "customerservice/FAQ";
	}
	
	@GetMapping("/notice")
	public String notice() {
		
		return "customerservice/notice";
	}
	
	@GetMapping("/service")
	public String service1() {
		
		return "customerservice/questionBoard";
	}
	
	
	
}
