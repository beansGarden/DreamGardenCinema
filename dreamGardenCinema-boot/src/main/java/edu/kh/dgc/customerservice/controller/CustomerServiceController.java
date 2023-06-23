package edu.kh.dgc.customerservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.customerservice.model.service.CustomerService;
import edu.kh.dgc.notice.model.dto.Notice;

@RequestMapping("/customerservice")
@Controller
public class CustomerServiceController {

	@Autowired
	private CustomerService service;
	
	
	// 고객센터 메인 페이지(FAQ 화면)
	@GetMapping("/")
	public String main(Model model, FAQ faq) {
		
		List<FAQ> FAQList = service.main(faq);
		
		model.addAttribute("FAQList", FAQList);
		
//		System.out.println(FAQList);
		
		return "customerservice/FAQ";
		
	}
	
	// 공지사항 전체 목록 출력
	@GetMapping("/notice")
	public String notice(Model model, Notice notice) {
		
		List<Notice> list = service.noticeList(notice);
		
		model.addAttribute("list", list);
		
//			System.out.println(list);
		
		return "customerservice/notice";
	}
	
	// 공지사항 해당 게시글 내용 보기
		@GetMapping("/noticeCon/{noticeNo}")
		public String noticeList(Model model, 
						@PathVariable(value = "noticeNo", required = false) String noticeNo
						) {
//			System.out.println(noticeNo);
			
			List<Notice> noticeList = service.noticeSelect(noticeNo);
			
			model.addAttribute("noticeList",noticeList);
			
			return "customerservice/notice-con";
		}
		
	// 1:1문의 작성하기
		
		
		
		
	
	// 멤버십 이용 약관 페이지
	@GetMapping("/terms")
	public String terms() {
		
		return "customerservice/membership-info";
	}
	
	// 메인 페이지
	@GetMapping("/FAQ")
	public String FAQ() {
		
		return "customerservice/FAQ";
	}
	
	
	// 1:1 문의 페이지
	@GetMapping("/service")
	public String service1() {
		
		return "customerservice/questionBoard";
	}
	
	
	
	
	
	
}
