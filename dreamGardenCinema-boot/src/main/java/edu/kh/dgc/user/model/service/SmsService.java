package edu.kh.dgc.user.model.service;

import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.dgc.common.utility.RedisUtil;
import edu.kh.dgc.user.model.dto.MessageDTO;
import edu.kh.dgc.user.model.dto.SmsRequestDTO;
import edu.kh.dgc.user.model.dto.SmsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PropertySource("classpath:config.properties")
@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {
	
	@Value("${naver-cloud-sms.accessKey}")
	private String accessKey;

	@Value("${naver-cloud-sms.secretKey}")
	private String secretKey;

	@Value("${naver-cloud-sms.serviceId}")
	private String serviceId;

	@Value("${naver-cloud-sms.senderPhone}")
	private String phone;
	
	// 현재시간
    String time = Long.toString(System.currentTimeMillis());
    
    private final RedisUtil redisUtil;

	public String makeSignature(Long time)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		String space = " ";
		String newLine = "\n";
		String method = "POST";
		String url = "/sms/v2/services/" + this.serviceId + "/messages";
		String timestamp = time.toString();
		String accessKey = this.accessKey;
		String secretKey = this.secretKey;

		String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
				.append(newLine).append(accessKey).toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		Base64.Encoder encoder = Base64.getEncoder();
		String encodeBase64String = encoder.encodeToString(rawHmac);
//		String encodeBase64String = Base64.encodeBase64String(rawHmac);

		return encodeBase64String;
	}
	
	public SmsResponseDTO sendSms(MessageDTO messageDto, String userTel) 
			throws JsonProcessingException, RestClientException, URISyntaxException
			, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String smsConfirmNum = createSmsKey();
		
		Long time = System.currentTimeMillis();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ncp-apigw-timestamp", time.toString());
		headers.set("x-ncp-iam-access-key", accessKey);
		headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
		
		messageDto.setTo(userTel);
		
		List<MessageDTO> messages = new ArrayList<>();
		messages.add(messageDto);
		
		SmsRequestDTO request = SmsRequestDTO.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from(phone)
				.content("[DGCinema] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
				.messages(messages)
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(request);
		HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
		
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	    SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDTO.class);
	    
	    redisUtil.setDataExpire(messageDto.getTo(), smsConfirmNum, 60 * 5L); // 유효시간 5분
//	    redisUtil.hset("smsConfirmNum", smsConfirmNum, messageDto.getTo());
	    
	    System.out.println("messageDto.getTo() : " + messageDto.getTo()); // redis 인증번호 value
	    System.out.println("smsConfirmNum1 : " + smsConfirmNum); // redis 인증번호 key
	    
	    return response;
	}

	// 6자리 난수
	public static String createSmsKey() {
		SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
	}

}