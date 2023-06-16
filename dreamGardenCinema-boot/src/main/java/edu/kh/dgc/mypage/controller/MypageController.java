package edu.kh.dgc.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/myPage")
@Controller
public class MypageController {

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
	public String inquiry() {
		
		return "myPage/my-page-inquiry";
	}
	
}
