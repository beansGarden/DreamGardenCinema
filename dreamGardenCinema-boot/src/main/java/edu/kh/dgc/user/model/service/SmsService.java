//package edu.kh.dgc.user.model.service;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import edu.kh.dgc.user.model.dto.MessageDto;
//import edu.kh.dgc.user.model.dto.SmsRequestDto;
//import edu.kh.dgc.user.model.dto.SmsResponseDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@PropertySource("classpath:config.properties")
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class SmsService {
//	
//    private final String smsConfirmNum = createSmsKey();
////    private final RedisUtill redisUtil;
//
//	@Value("${naver-cloud-sms.accessKey}")
//	private String accessKey;
//
//	@Value("${naver-cloud-sms.secretKey}")
//	private String secretKey;
//
//	@Value("${naver-cloud-sms.serviceId}")
//	private String serviceId;
//
//	@Value("${naver-cloud-sms.senderPhone}")
//	private String phone;
//	
//    public SmsResponseDto sendSms(MessageDto messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        String time = Long.toString(System.currentTimeMillis());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("x-ncp-apigw-timestamp", time);
//        headers.set("x-ncp-iam-access-key", accessKey);
//        headers.set("x-ncp-apigw-signature-v2", getSignature(time)); // signature 서명
//
//        List<MessageDto> messages = new ArrayList<>();
//        messages.add(messageDto);
//
//        SmsRequestDto request = SmsRequestDto.builder()
//                .type("SMS")
//                .contentType("COMM")
//                .countryCode("82")
//                .from(phone)
//                .content("[DGC 테스트] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
//                .messages(messages)
//                .build();
//
//        //쌓은 바디를 json형태로 반환
//        ObjectMapper objectMapper = new ObjectMapper();
//        String body = objectMapper.writeValueAsString(request);
//        // jsonBody와 헤더 조립
//        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        //restTemplate로 post 요청 보내고 오류가 없으면 202코드 반환
//        SmsResponseDto smsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDto.class);
//        SmsResponseDto responseDto = new SmsResponseDto(smsConfirmNum);
//       // redisUtil.setDataExpire(smsConfirmNum, messageDto.getTo(), 60 * 3L); // 유효시간 3분
//        return smsResponseDto;
//	}
//
//    public String getSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
//        String space = " ";
//        String newLine = "\n";
//        String method = "POST";
//        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
//        String accessKey = this.accessKey;
//        String secretKey = this.secretKey;
//
//        String message = new StringBuilder()
//                .append(method)
//                .append(space)
//                .append(url)
//                .append(newLine)
//                .append(time)
//                .append(newLine)
//                .append(accessKey)
//                .toString();
//
//        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(signingKey);
//
//        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//        String encodeBase64String = Base64.encodeBase64String(rawHmac);
//
//        return encodeBase64String;
//    }
//
////5자리의 난수를 조합을 통해 인증코드 만들기
//	public static String createSmsKey() {
//		StringBuffer key = new StringBuffer();
//		Random rnd = new Random();
//
//		for (int i = 0; i < 5; i++) { // 인증코드 5자리
//			key.append((rnd.nextInt(10)));
//		}
//		return key.toString();
//	}
//}