package com.example.javaormfour;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;




@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        var currentTextBuffer = container.getMaxTextMessageBufferSize();
        var currentBinaryBuffer = container.getMaxBinaryMessageBufferSize();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        var webSocketHandler = new WebSocketService();
        registry.addHandler(webSocketHandler, "/ws");
    }

}

