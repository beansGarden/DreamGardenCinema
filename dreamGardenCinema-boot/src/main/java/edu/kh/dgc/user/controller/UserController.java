package edu.kh.dgc.user.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.common.utility.RedisUtil;
import edu.kh.dgc.user.model.dto.User;
import edu.kh.dgc.user.model.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "loginUser" })
@RequiredArgsConstructor
public class UserController {

	private final RedisUtil redisUtil;

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
	

	@GetMapping("/changePw")
	public String changePw(HttpSession session) {
		
		if(session.getAttribute("cngPwUserId") == null) {
			return "common/error";
		}
		return "user/changePw";
	}


	@PostMapping("/login")
	public String login(User inputUser, Model model, 
			@RequestHeader(value = "referer") String referer,
			@RequestParam(value = "saveId", required = false) String saveId,
			HttpServletResponse resp,
			RedirectAttributes ra) {

		User loginUser = service.login(inputUser);
		System.out.println(loginUser);
		String path = "redirect:";

		if (loginUser != null) {
			System.out.println(loginUser.getUserId() + "  로그인 성공");
			
			path += "/";
			model.addAttribute("loginUser", loginUser);
			Cookie cookie = new Cookie("saveId", loginUser.getUserId());

			if (saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				cookie.setMaxAge(0);
			}
			
//			if(loginUser.getUserRole().toUpperCase().equals("A")) { // 관리자 로그인 시 관리자페이지 이동
//				path = "redirect:/admin";
//			}

			cookie.setPath("/");
			resp.addCookie(cookie);
		} else {
			path += referer;
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		return path;
	}

	@PostMapping("/signUp")
	public String signup(HttpSession session, RedirectAttributes ra, @Valid User inputUser, Model model) {

		String path = "redirect:";
		String message = null;
		String redisKey = "smsConfirmNum";

		inputUser.setUserBirth(LocalDate.parse(inputUser.getUserBirth1(), DateTimeFormatter.ofPattern("yyyyMMdd")));
		boolean result1 = service.checkOverlap(inputUser); // 중복체크
		
		if(!inputUser.getUserPw().equals(inputUser.getUserRePw())) {
			path = "user/signUp";
			message = "회원 가입 실패";
			ra.addFlashAttribute("message", message);
			return path;
		}

		if (redisUtil.getData(inputUser.getUserTel()) == null) {
//			System.out.println("인증번호 == null");
			path = "user/signUp";
			message = "인증번호가 다릅니다.";
			ra.addFlashAttribute("message", message);
			return path;
		} else {
			if (!redisUtil.getData(inputUser.getUserTel()).equals(inputUser.getAuthKey())) {
//				System.out.println("인증번호 != 폰번호");
				path = "user/signUp";
				message = "인증번호가 다릅니다.";
				ra.addFlashAttribute("message", message);
				return path;
			}
			System.out.println("인증번호 같음");
			redisUtil.deleteData(inputUser.getUserTel());
		}

		int result = service.signup(inputUser); // DB insert

		if (result > 0 && result1 == true) {
			path += "/user/login";

			message = inputUser.getUserNickname() + "님의 가입을 환영합니다.";

		} else {
			path = "user/signUp";
			message = "회원 가입 실패";
		}

		ra.addFlashAttribute("message", message);

		return path;
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status, HttpSession session) {
		status.setComplete();
		return "redirect:/";
	}
	
	@PostMapping("/findPassword")
	public String findPassword(User inputUser, String userTel2, 
			RedirectAttributes ra,
			HttpSession session) {
		
		inputUser.setUserTel(userTel2);
		
		String path = "redirect:";
		String message = null;
		
		if (redisUtil.getData(inputUser.getUserTel()) == null) {
//			System.out.println("인증번호 == null");
			path = "user/accountFind";
			message = "인증번호가 다릅니다.";
			ra.addFlashAttribute("message", message);
			return path;
		} else {
			if (!redisUtil.getData(inputUser.getUserTel()).equals(inputUser.getAuthKey())) {
//				System.out.println("인증번호 != 폰번호");
				path += "user/accountFind";
				message = "인증번호가 다릅니다.";
				ra.addFlashAttribute("message", message);
				return path;
			}
//			System.out.println("인증번호 같음");
			session.setAttribute("cngPwUserId", inputUser.getUserId());
			redisUtil.deleteData(inputUser.getUserTel());
			path += "/user/changePw";
		}

		return path;
	}
	
	@PostMapping("/changePassword")
	public String changePassword(User inputUser, 
			RedirectAttributes ra,
			HttpSession session) {
		
		inputUser.setUserId((String)session.getAttribute("cngPwUserId"));
		
		String path = "redirect:";
		String message = null;
		
		int result = service.changePw(inputUser.getUserPw(), inputUser.getUserRePw(), inputUser.getUserId());
		
		if(result > 0) {
			session.removeAttribute("cngPwUserId");
			message = "비밀번호 변경에 성공했습니다.";
			ra.addFlashAttribute("message", message);
			path += "/login";
		}else {
			message = "비밀번호 변경에 실패했습니다.";
			ra.addFlashAttribute("message", message);
			path += "/user/changePw";
		}
		return path;
	}
	


}
