package edu.kh.dgc.mypage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.dgc.mypage.model.service.MypageService;
import edu.kh.dgc.user.model.dto.User;

@SessionAttributes({"loginUser"})
@RequestMapping("/myPage")
@RestController
public class MypageAjaxController {

	@Autowired
	private MypageService service;
	
	@PostMapping("/secessionCheck")
	@ResponseBody
	public int secessionCheck(
			@RequestBody Map<String,String> paramMap
			,@SessionAttribute("loginUser") User loginuser
			) {
		
		int userNo = loginuser.getUserNo();
		
		paramMap.get("secessionPwValue");
		
		
		int result = service.secessionCheck(userNo,paramMap.get("secessionPwValue"));
		
		
		if(result > 0) {
			return result;
		}else{
			return 0;
		}
		
	}
}
