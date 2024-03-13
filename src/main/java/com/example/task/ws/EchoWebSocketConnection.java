package com.example.task.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
@RequiredArgsConstructor
public class EchoWebSocketConnection extends TextWebSocketHandler {
    private WebSocketSession webSocketSession;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Value("${echo.websocket.url}")
    private String echoWebSocketUrl;

    public void connect() {
        try {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            webSocketSession = webSocketClient.execute(
                    this, null, URI.create(echoWebSocketUrl)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (webSocketSession != null && webSocketSession.isOpen()) {
            try {
                webSocketSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void echo(Message message) {
        if (webSocketSession != null && webSocketSession.isOpen()) {
            simpMessagingTemplate.convertAndSendToUser(message.username(), "/topic", message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("Message received from server: " + message.getPayload());
    }
}
