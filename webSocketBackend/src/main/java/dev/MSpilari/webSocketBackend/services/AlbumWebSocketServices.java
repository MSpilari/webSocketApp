package dev.MSpilari.webSocketBackend.services;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import dev.MSpilari.webSocketBackend.models.Album;
import dev.MSpilari.webSocketBackend.repositories.AlbumRepository;

@Service
public class AlbumWebSocketServices {

    private final SimpMessagingTemplate messagingTemplate;
    private final AlbumRepository albumRepository;

    public AlbumWebSocketServices(SimpMessagingTemplate messagingTemplate, AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAllAlbumsToClients() {
        List<Album> allAlbums = albumRepository.getAllAlbums();
        messagingTemplate.convertAndSend("/topic/albums", allAlbums);
    }

    public void broadcastNewAlbum(Album album) {
        messagingTemplate.convertAndSend("/topic/albums", album);
    }
}
