package edu.kh.dgc.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.kh.dgc.user.model.dto.MessageDto;
import edu.kh.dgc.user.model.dto.SmsResponseDto;
import edu.kh.dgc.user.model.service.SmsService;
import lombok.RequiredArgsConstructor;

//@RequestMapping("/sendSms")
//@SessionAttributes("authKey")
@RestController
@RequiredArgsConstructor
public class SmsController {
	private final SmsService smsService;
    
    @PostMapping("/sms/send")
    public SmsResponseDto sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException
    , URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        SmsResponseDto responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }
}