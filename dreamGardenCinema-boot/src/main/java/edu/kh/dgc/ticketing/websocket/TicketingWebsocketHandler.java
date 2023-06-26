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
import edu.kh.dgc.ticketing.model.dto.Ticket;
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
	
	// 웹소켓 연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Socket 연결");
		sessions.add(session);
		sessionList.add(session.getAttributes());  // 로그인 정보, 세션아이디, 유저세션(세션)
	}

	// 예매 2페이지 좌석선택 삽입,삭제 웹소켓
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 가져온 메시지를 seatCheck 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Ticket ticket = objectMapper.readValue(message.getPayload().substring(10), Ticket.class);  // room, movieTime, movieTheater, movieNo, seatNo, userNo, checked
		ticket.setSeatNo(message.getPayload().split("\"")[31]);
		
		String seatResult = service.seatCheck(ticket);  // INSERT, DELETE 결과를 가져오는 서비스
		
		
		for(int i=0;i<sessions.size();i++){  // List 하나씩 꺼내기
			
			String room = (String) sessionList.get(i).get("room");  // 접속유저들의 방번호
			
			if(!sessionList.get(i).get("userSession").equals(session.getAttributes().get("userSession")) && session.getAttributes().get("room").equals(room)) {  // 세션 아이디가 같지 않으면  &&  메세지 전달한 사람과 룸번호가 같으면
				
				WebSocketSession sess = sessions.get(i);
				
				TextMessage sendMsg = null;
				if(seatResult.equals("예매성공")) {
					sendMsg = new TextMessage(ticket.getSeatNo()+" alreadyChk");
					sess.sendMessage(sendMsg);
				} else if(seatResult.equals("예매취소성공")) {
					sendMsg = new TextMessage(ticket.getSeatNo()+" cancelChk");
					sess.sendMessage(sendMsg);
				} else {
					sendMsg = new TextMessage("fail");
					sess.sendMessage(sendMsg);
				}
					
			}
				
		}
	}

	// 예매 2페이지 Web Socket 연결 해제 시 데이터 삭제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Socket 연결 해제");
		
		int userNo = ((User)session.getAttributes().get("loginUser")).getUserNo();
		
		System.out.println(userNo);
		
		
		Map<String, Object> resultMap = service.seatDelete(userNo);
		String seatResult = (String) resultMap.get("seatResult");
		List<Ticket> seatCheckList = (List<Ticket>) resultMap.get("seatCheckList");
		int idx = -1;
		for(int i=0;i<sessions.size();i++){
			if(seatResult.equals("예매취소성공")) {
				WebSocketSession sess = sessions.get(i);
				if(!sessionList.get(i).get("userSession").equals(session.getAttributes().get("userSession"))) {  // 세션 아이디가 다른 접속자
					TextMessage sendMsg = null;
						for(Ticket seatCheck : seatCheckList) {
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