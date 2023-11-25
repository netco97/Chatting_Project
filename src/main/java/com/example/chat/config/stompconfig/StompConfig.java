package com.example.chat.config.stompconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").setHandshakeHandler(new CustomHandshakeHandler()).withSockJS();
        //CORS(Cross Origin Resource Sharing "*" 실제 배포할 도메인주소)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/sub", "/queue");

        // /pub으로 시작하는 STOMP 메세지의 "destination" 헤더는 @Controller 객체의 @MessageMapping 메서드로 라우팅.
        registry.setApplicationDestinationPrefixes("/pub");

        // specific user endpoint prefix (default /user)
        registry.setUserDestinationPrefix("/user");
    }
}
