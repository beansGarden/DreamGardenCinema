package edu.kh.dgc.ticketing.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Socket 연결");
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 가져온 메시지를 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		SeatCheck seatCheck = objectMapper.readValue(message.getPayload(), SeatCheck.class);
		System.out.println(seatCheck);
		
		
		int result = service.seatCheck(seatCheck);
		
		System.out.println(12);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
}
