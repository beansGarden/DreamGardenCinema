package edu.kh.project.user.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.user.model.dto.User;
import edu.kh.project.user.model.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
@SessionAttributes({ "loginUser" })
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/signUp")
	public String signup() {

		return "user/signUp";
	}
	
	@GetMapping("/accountFind")
	public String pwSearch() {

		return "user/accountFind";
	}
	
	@PostMapping("/login")
	public String login(User inputUser, Model model
						, @RequestHeader(value = "referer") String referer
						, @RequestParam(value = "saveId", required = false) String saveId
						, HttpServletResponse resp
						, RedirectAttributes ra) {
		
		User loginUser = service.login(inputUser);
		
//		Member inputUser = new Member();
//		inputUser.setMemberEmail("pingpong@kh.or.kr");
//		inputUser.setMemberNo(1);
		String path = "redirect:";
		
		if(loginUser != null) { 
			System.out.println(loginUser.getUserId() + "  로그인 성공");
//			if(inputUser.getUserRole().toUpperCase().equals("A")) { // 관리자 로그인 시 관리자페이지 이동
//				path += "/manager";
//			}
			path += "/";
			model.addAttribute("inputUser", loginUser);
			Cookie cookie = new Cookie("saveId", loginUser.getUserEmail());
			
			if(saveId != null) {
				cookie.setMaxAge(60*60*24*30);
			}else {
				cookie.setMaxAge(0);
			}
					
			cookie.setPath("/"); 
			resp.addCookie(cookie);
		}else {
			path += referer;
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		return path;
	}
	
	@PostMapping("/signUp")
	public String signup(
			HttpSession session, RedirectAttributes ra, User inputUser, Model model) {
		
		String path = "redirect:";
		String message = null;
		
		inputUser.setUserBirth(LocalDate.parse(inputUser.getUserBirth1(), DateTimeFormatter.ofPattern("yyyyMMdd")));
		
		int result = service.signup(inputUser);
		
		if (result > 0) {
			path += "/user/login";
			
			message = inputUser.getUserNickname() + "님의 가입을 환영합니다.";

		} else {
			path += "signUp"; 
			message = "회원 가입 실패";
		}
		
		ra.addFlashAttribute("message", message);

		return path;
	}
	
}
