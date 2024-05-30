package com.example.urinoirapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().permitAll()
                .simpDestMatchers("/app/**").permitAll()
                .simpSubscribeDestMatchers("/topic/**").permitAll()
                .anyMessage().denyAll();
    }
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}