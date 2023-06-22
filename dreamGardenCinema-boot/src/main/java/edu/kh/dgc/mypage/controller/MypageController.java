package edu.kh.dgc.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 닉네임 변경
	@PostMapping("/change-nickname")
	public String changeNickName(
			@SessionAttribute("loginUser") User loginuser
			,@RequestParam String redirectUrl
			,String userNickname
			,RedirectAttributes ra
			) {
		System.out.println(userNickname);
		
		loginuser.setUserNickname(userNickname);
		
		int result = service.changeNickName(loginuser);
		
		
		System.out.println(loginuser.getUserNickname());
		
		ra.addAttribute("message","닉네임이 변경되었습니다.");
		
		return "redirect:" + redirectUrl;
	}
	
	// 내정보 수정
	@PostMapping("/change-info")
	public String changeInfo(
			User updateUser
			,String[] memberAddress
			,String userEmail
			,String checkPw
			,String userPw
			,RedirectAttributes ra
			,@SessionAttribute("loginUser") User loginUser
			) {
		
		String addr = String.join("^^^", memberAddress);
		
		updateUser.setUserAddress(addr);
		
		updateUser.setUserNo(loginUser.getUserNo());
		
		int userNo = loginUser.getUserNo();
		
		int result = service.changeInfo(updateUser);
		
		String message = null;
		String path = "redirect:";
		
		if(result>0) {
			
			loginUser.setUserEmail(updateUser.getUserEmail());
			loginUser.setUserAddress(updateUser.getUserAddress());
			
			int result2 = service.changePw(checkPw,userNo,userPw);
				
				if(result2>0) {
					message = "회원 정보가 수정되었습니다.";
					path += "/";
				}else {
					message = "현재 비밀번호가 일치하지않습니다.";
					path +="/myPage/";
				}
		}else {
			
			message = "회원 정보 수정 실패";
			path += "/";
		}
		
		ra.addFlashAttribute("message",message);
		
		return path;
	}
}
