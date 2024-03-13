package com.example.task.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@Slf4j
public class MessageController {

    private final EchoWebSocketConnection echoWebSocketConnection;

    public MessageController(EchoWebSocketConnection echoWebSocketConnection) {
        this.echoWebSocketConnection = echoWebSocketConnection;
    }

    @MessageMapping("/ws")
    public void handleWebSocketMessage(Message message) {
        log.info("Message received: {}", message);
        echoWebSocketConnection.echo(message);
    }

    @EventListener
    public void handleConnectEvent(SessionConnectedEvent event) {
        echoWebSocketConnection.connect();
    }

    @EventListener
    public void handleDisconnectEvent(SessionDisconnectEvent event) {
        echoWebSocketConnection.disconnect();
    }
}
