package edu.kh.dgc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import edu.kh.dgc.ticketing.websocket.TicketingWebsocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	private final TicketingWebsocketHandler ticketSock;
	
	public WebSocketConfig(TicketingWebsocketHandler ticketSock) {
        this.ticketSock = ticketSock;
    }

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ticketSock, "/click").setAllowedOrigins("*");
    }
	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		
//		registry.addHandler(ticketSock, "/click").withSockJS();
//		registry.addHandler(ticketSock, "/ws/click").setAllowedOrigins("http://localhost:80").withSockJS();
//		
//	}
	
}
