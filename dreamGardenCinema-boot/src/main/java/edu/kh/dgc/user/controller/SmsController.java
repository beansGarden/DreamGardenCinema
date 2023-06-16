package edu.kh.dgc.user.controller;



import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
 
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.kh.dgc.user.model.dto.MessageDTO;
import edu.kh.dgc.user.model.dto.SmsResponseDTO;
import edu.kh.dgc.user.model.dto.User;
import edu.kh.dgc.user.model.service.SmsService;
import lombok.RequiredArgsConstructor;
 
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class SmsController {
	
	private final SmsService smsService;
	
	@GetMapping("/send")
	public String getSmsPage() {
		return "user/sendSms";
	}
	
	@ResponseBody
	@PostMapping("/sms/send")
	public SmsResponseDTO sendSms(MessageDTO messageDto, Model model, String userTel) throws JsonProcessingException, RestClientException
	, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		SmsResponseDTO response = smsService.sendSms(messageDto, userTel);
		//model.addAttribute("response", response);
		return response;
	}
 
	
}