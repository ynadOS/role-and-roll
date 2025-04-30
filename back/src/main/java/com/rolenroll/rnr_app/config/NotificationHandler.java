package com.rolenroll.rnr_app.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

public class NotificationHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Exemple de message reçu, à traiter et envoyer à tous les clients
        String notificationMessage = "Mise à jour dans une campagne";
        session.sendMessage(new TextMessage(notificationMessage));  // Envoi de la notification
    }
}