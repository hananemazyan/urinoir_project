package com.example.urinoirapp.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Récupérer le contenu du message
        String payload = message.getPayload();

        // Faire quelque chose avec le contenu du message
        System.out.println("Message reçu : " + payload);

        // Répondre au client si nécessaire
        session.sendMessage(new TextMessage("Message reçu avec succès : " + payload));
    }
}
