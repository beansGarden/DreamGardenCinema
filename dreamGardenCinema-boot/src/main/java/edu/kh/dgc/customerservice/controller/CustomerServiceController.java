package edu.kh.dgc.customerservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.customerservice.model.service.CustomerService;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.user.model.dto.User;

@RequestMapping("/customerservice")
@Controller
@SessionAttributes({"loginUser"})
public class CustomerServiceController {

	@Autowired
	private CustomerService service;
	
	
	// 고객센터 메인 페이지(FAQ 화면)
	@GetMapping("/")
	public String main(Model model, FAQ faq) {
		
		List<FAQ> FAQList = service.main(faq);
		
		model.addAttribute("FAQList", FAQList);
		
		return "customerservice/FAQ";
		
	}
	
	// 공지사항 전체 목록 출력
	@GetMapping("/notice")
	public String notice(Model model, Notice notice) {
		
		List<Notice> list = service.noticeList(notice);
		
		model.addAttribute("list", list);
		
		return "customerservice/notice";
	}
	
	// 공지사항 해당 게시글 내용 보기
		@GetMapping("/noticeCon/{noticeNo}")
		public String noticeList(Model model, 
						@PathVariable(value = "noticeNo", required = false) String noticeNo
						) {
			
			List<Notice> noticeList = service.noticeSelect(noticeNo);
			
			model.addAttribute("noticeList",noticeList);
			
			return "customerservice/notice-con";
		}
		
	// 1:1문의 작성하기
	@PostMapping("/QAinsert")
	public String cusQAInsert(Qna qna, String QAAgree 
				, @RequestParam(value="images", required=false) List<MultipartFile> images
				, @SessionAttribute("loginUser") User loginUser, RedirectAttributes ra
				, @RequestParam(value="key", required=false)String selectedValue) throws IllegalStateException, IOException {

		
		// 1. 로그인한 회원 번호를 얻어와 Qna에 세팅
		qna.setUserNo(loginUser.getUserNo());
		
		System.out.println(qna);
		
		int qnaNo = service.cusQAInsert(qna, images, selectedValue);
		
//			int nonQnaNo = service.nonMemInsert(nonQna, images);
		
		String message = null;
		String path = "redirect:";
		
		if(qnaNo > 0 ) { // 삽입성공시 || nonQnaNo > 0
			
			message = "1:1문의가 접수 되었습니다";
			path += "/customerservice/";
			
		}else {
			message = "1:1문의 접수 실패";
			path += "/customerservice/questionBoard";
		}
		
		ra.addFlashAttribute("message" ,message);
		
		return path;
	}
		
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
