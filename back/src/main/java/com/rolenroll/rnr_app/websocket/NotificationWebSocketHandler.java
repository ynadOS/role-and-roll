package com.rolenroll.rnr_app.websocket;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // Logique à exécuter une fois la connexion WebSocket établie
        System.out.println("WebSocket connecté : " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Gestion des messages reçus (par exemple, le message d'un client)
        System.out.println("Message reçu: " + message.getPayload());
    }

    public void sendNotification(String message, WebSocketSession session) throws Exception {
        // Envoie d'un message à un client via WebSocket
        session.sendMessage(new TextMessage(message));
    }
}