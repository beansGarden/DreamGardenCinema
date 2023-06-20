package com.example.websock.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.websock.websockehandler.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSockConfig implements WebSocketConfigurer{
	
	private final WebSocketHandler webSocketHandler;
	
	public WebSockConfig(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/click").setAllowedOrigins("*");
    }

	
}
