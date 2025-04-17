package dev.MSpilari.webSocketBackend.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import dev.MSpilari.webSocketBackend.services.AlbumWebSocketServices;

@Controller
public class AlbumWebSocketController {

    private final AlbumWebSocketServices webSocketService;

    public AlbumWebSocketController(AlbumWebSocketServices webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/albums")
    public void handleGetAlbumsRequest() {
        webSocketService.sendAllAlbumsToClients();
    }
}
