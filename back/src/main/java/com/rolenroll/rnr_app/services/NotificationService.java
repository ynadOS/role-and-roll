package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.websocket.NotificationWebSocketHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class NotificationService {

    private final NotificationWebSocketHandler webSocketHandler;

    public NotificationService(NotificationWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void sendCampaignUpdateNotification(String campaignId, String updateDetails) throws Exception {
        // Supposons que vous ayez une session WebSocket spécifique pour un utilisateur ou un groupe
        WebSocketSession session = getSessionForCampaign(campaignId);

        if (session != null && session.isOpen()) {
            String message = "Mise à jour de la campagne : " + updateDetails;
            webSocketHandler.sendNotification(message, session);
        }
    }

    private WebSocketSession getSessionForCampaign(String campaignId) {
        // Cette méthode devrait récupérer la session WebSocket pour l'utilisateur lié à la campagne
        return null;  // Implémentation à faire en fonction de votre logique (ex : mapping utilisateur-campagne)
    }
}