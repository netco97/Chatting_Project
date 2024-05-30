# Chatting App

### WebSocket STOMP
- STOMP 기반의 통신흐름
![1](https://github.com/netco97/Chatting_Project/assets/101931428/7b677f0d-4e86-46d7-9ea6-07f14faf0465)

### 특정 유저 메세지 보내기
- @SendToUser Config
```java
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
        // 나는 /sub는 일반 메세지 통신, /queue는 일부 유저(sendtouser)로 사용할거임
        registry.enableSimpleBroker("/sub", "/queue");

        // /pub으로 시작하는 STOMP 메세지의 "destination" 헤더는 @Controller 객체의 @MessageMapping 메서드로 라우팅.
        registry.setApplicationDestinationPrefixes("/pub");

        // specific user endpoint prefix (default /user)
        registry.setUserDestinationPrefix("/user");
    }

}
```

- @SendToUser 또는 convertAndSendToUser
```java
  private final SimpMessageSendingOperations messagingTemplate;
  messagingTemplate.convertAndSendToUser(user, "/queue/"+message.getRoomId(),message);
```

### 특정 유저 메세지 보내기 문제점 발생
- 1. convertAndSendToUser를 사용하기 위해 불러 오는 객체 SimpleMessagingTemplate은 AbstractMessageSendingTemplate을 상속받는다.
- 2. AbstractMessageSendingTemplate 은 메시지를 전송하기 위한 header 세팅을 별도로 진행 해준다.
- 3. SimpleMessagingTemplate 은 Native header가 있을 경우, 같은 header로 return 해주는데 이 때 내가 가지고 있던 헤더가 HashMap 바뀌게 되어 동작을 하지 않는 것이다. 우리가 원래의 header 정보를 사용하고 싶다면, Messageheader 타입의 헤더정보가 필요하다.
- 4. 즉, 메시지 전송을 위해 헤더 세팅을 별도로 해주어야 정상적으로 메시지를 보낼 수 있다.



