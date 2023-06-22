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
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketingWebsocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private TicketingService service;

	private List<WebSocketSession> sessions = new ArrayList<>();
	
	private List<Map<String, Object>> sessionList = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Socket 연결");
		sessions.add(session);
		sessionList.add(session.getAttributes());  // 로그인 정보, 세션아이디, 유저세션(세션)
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 가져온 메시지를 seatCheck 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		SeatCheck seatCheck = objectMapper.readValue(message.getPayload(), SeatCheck.class);  // room, movieTime, movieTheater, movieNo, seatNo, userNo, checked
		
		String seatResult = service.seatCheck(seatCheck);  // INSERT, DELETE 결과를 가져오는 서비스
		
		for(int i=0;i<sessions.size();i++){  // List 하나씩 꺼내기
			
			String room = (String) sessionList.get(i).get("room");  // 접속유저들의 방번호
			
			if(!sessionList.get(i).get("userSession").equals(session.getAttributes().get("userSession")) && seatCheck.getRoom().equals(room)) {  // 세션 아이디가 같지 않으면  &&  메세지 전달한 사람과 룸번호가 같으면
				
				WebSocketSession sess = sessions.get(i);
				
				TextMessage sendMsg = null;
				System.out.println(seatResult);
				if(seatResult.equals("예매성공")) {
					sendMsg = new TextMessage(seatCheck.getSeatNo()+" alreadyChk");
					sess.sendMessage(sendMsg);
				} else if(seatResult.equals("예매취소성공")) {
					sendMsg = new TextMessage(seatCheck.getSeatNo()+" cancelChk");
					sess.sendMessage(sendMsg);
				} else {
					sendMsg = new TextMessage("fail");
					sess.sendMessage(sendMsg);
				}
					
			}
				
		}
	}

	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Socket 연결 해제");
		
		int userNo = ((User)session.getAttributes().get("loginUser")).getUserNo();
		System.out.println(userNo);
		Map<String, Object> resultMap = service.seatDelete(userNo);
		String seatResult = (String) resultMap.get("seatResult");
		List<SeatCheck> seatCheckList = (List<SeatCheck>) resultMap.get("seatCheckList");
		int idx = -1;
		for(int i=0;i<sessions.size();i++){
			WebSocketSession sess = sessions.get(i);
			if(seatResult.equals("예매취소성공")) {
				if(!sessionList.get(i).get("userSession").equals(session.getAttributes().get("userSession"))) {  // 세션 아이디가 다른 접속자
					TextMessage sendMsg = null;
						for(SeatCheck seatCheck : seatCheckList) {
							sendMsg = new TextMessage(seatCheck.getSeatNo()+" cancelChk");
							sess.sendMessage(sendMsg);					
						}
				} else {
					idx = i;
				}
			} else {
				idx = i;
			}
		}
		sessionList.remove(idx);
		sessions.remove(idx);
		
	}
	
	
}