package edu.kh.dgc.user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.user.model.service.AjaxService;

@Controller
public class AjaxController {
	
	@Autowired
	private AjaxService service;
	// 아이디 중복 검사
	@GetMapping("/dupCheck/id")
	@ResponseBody
	public int checkId(String id) {
		return service.checkId(id); 
	}
	
	// 이메일 중복 검사
	@GetMapping("/dupCheck/email")
	@ResponseBody
	public int checkEmail(String email) {
		return service.checkEmail(email); 
	}
	
	// 닉네임 중복 검사
	@GetMapping("/dupCheck/nickname")
	@ResponseBody
	public int checkNickname(String nickname) {
		return service.checkNickname(nickname); 
	}
	
}
