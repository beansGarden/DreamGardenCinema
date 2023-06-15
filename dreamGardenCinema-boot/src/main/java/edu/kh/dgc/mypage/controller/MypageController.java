package edu.kh.dgc.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.mypage.model.service.MypageService;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.user.model.dto.User;

@SessionAttributes({"loginUser"})
@RequestMapping("/myPage")
@Controller
public class MypageController {

	@Autowired
	private MypageService service;
	
	@GetMapping("/")
	public String reservation() {
			
		return "myPage/my-page-reservation";
	}
	
	@GetMapping("/my-page-membership")
	public String membership() {
		
		return "myPage/my-page-membership";
	}
	@GetMapping("/my-page-coupon")
	public String coupon() {
		
		return "myPage/my-page-coupon.html";
	}
	@GetMapping("/my-page-inquiry")
	public String inquiry(
			Model model,
			@SessionAttribute("loginUser") User loginuser
			) {

		int userNo = loginuser.getUserNo();
		
		List<Qna> myQnaList = service.myQnaList(userNo); 
		
		model.addAttribute("myQnaList",myQnaList);
		
		return "myPage/my-page-inquiry";
	}
	
}
