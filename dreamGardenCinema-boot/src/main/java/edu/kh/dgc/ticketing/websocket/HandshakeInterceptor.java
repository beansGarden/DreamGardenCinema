package edu.kh.dgc.ticketing.websocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.service.UserService;
import jakarta.servlet.http.HttpSession;

@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{
	
	@Autowired
	private TicketingService service;

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		ServletServerHttpRequest req = (ServletServerHttpRequest)request;
		HttpSession session = req.getServletRequest().getSession();
		attributes.put("userSession", session );
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	
	

}
