package edu.kh.dgc.ticketing.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketingWebsocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private TicketingService service;

	private List<WebSocketSession> sessions = new ArrayList<>();
	
	private Map<String, WebSocketSession> userSessionMap = new HashMap<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Socket 연결");
		sessions.add(session);
		System.out.println(sessions);
		log.info(sendPushUsername(session));				//현재 접속한 사람의 username이 출력됨
		String senderId = sendPushUsername(session);
		userSessionMap.put(senderId, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String id = session.getId();  //메시지를 보낸 아이디
		log.info(id);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		SeatCheck seatCheck = objectMapper.readValue(message.getPayload(), SeatCheck.class);  // 가져온 메시지를 객체로 변환
		System.out.println(seatCheck);
	
		String seatResult = service.seatCheck(seatCheck);  // INSERT, DELETE 결과
		
		
		TextMessage sendMsg = null;
		for(WebSocketSession single : sessions) {
			
			if(seatResult.equals("이미선택")) {
				sendMsg = new TextMessage(seatCheck.getSeatNo()+" abc");
				single.sendMessage(sendMsg);
			}
			
			if(!single.getId().equals(id)) {
				
				System.out.println(single);
				if(seatResult.equals("예매성공")) {
					sendMsg = new TextMessage(seatCheck.getSeatNo()+" alreadyChk");
					single.sendMessage(sendMsg);
				} else if(seatResult.equals("예매취소성공")) {
					sendMsg = new TextMessage(seatCheck.getSeatNo()+" cancelChk");
					single.sendMessage(sendMsg);
				} else {
					sendMsg = new TextMessage("fail");
					single.sendMessage(sendMsg);
				}
			}
			
		}
		
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Socket 연결 해제");
		
		sessions.remove(session);
		userSessionMap.remove(sendPushUsername(session), session);
	}
	
	private String sendPushUsername(WebSocketSession session) {
		String loginUsername;
		
		if (session.getPrincipal() == null) {
			loginUsername = null;
		} else {
			loginUsername = session.getPrincipal().getName();
		}
		return loginUsername;
	}
	
}