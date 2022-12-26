package com.example.javaormfour;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class WebSocketService extends BinaryWebSocketHandler {
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        var textMessage = "hello world";
        session.sendMessage(new TextMessage(textMessage + "!"));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);

        ByteBuffer byteBuffer = message.getPayload();
        String s = StandardCharsets.UTF_8.decode(byteBuffer).toString();
        var textMessage = "reply:" + s;
        //web_socket
        session.sendMessage(new TextMessage(textMessage + "!"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }
}
