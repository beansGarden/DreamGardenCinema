package edu.kh.dgc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import edu.kh.dgc.ticketing.websocket.TicketingWebsocketHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{

	private final TicketingWebsocketHandler ticketSock;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(ticketSock, "/click").withSockJS();
		
	}
	
}
