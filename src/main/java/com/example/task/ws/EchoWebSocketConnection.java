package com.example.task.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class EchoWebSocketConnection extends TextWebSocketHandler {
    private WebSocketSession webSocketSession;

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

    public void echo(String message) {
        if (webSocketSession != null && webSocketSession.isOpen()) {
            try {
                webSocketSession.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Message received from server: " + message.getPayload());
    }
}
