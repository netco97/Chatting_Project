package com.example.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /ws-stomp 는 WebSocket 또는 SockJS Client가 웹소켓 핸드셰이크 커넥션을 생성할 경로.
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*").withSockJS();
        //CORS(Cross Origin Resource Sharing "*" 실제 배포할 도메인주소)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 내장된 메세지 브로커를 사용해 Client에게 Subscriptions , Broadcasting 기능을 제공한다. 또한 /sub로 시작하는
        // "destination" 헤더를 가진 메세지를 브로커로 라우팅.
        registry.enableSimpleBroker("/sub");

        // /pub으로 시작하는 STOMP 메세지의 "destination" 헤더는 @Controller 객체의 @MessageMapping 메서드로 라우팅.
        registry.setApplicationDestinationPrefixes("/pub");

    }

}
