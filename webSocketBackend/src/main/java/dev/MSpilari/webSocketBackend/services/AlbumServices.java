package dev.MSpilari.webSocketBackend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.MSpilari.webSocketBackend.models.Album;
import dev.MSpilari.webSocketBackend.repositories.AlbumRepository;
import dev.MSpilari.webSocketBackend.repositories.S3Repository;

@Service
public class AlbumServices {

    private final S3Repository s3Repository;
    private final AlbumRepository albumRepository;
    private final AlbumWebSocketServices webSocketService;

    public AlbumServices(S3Repository s3Repository, AlbumRepository albumRepository,
            AlbumWebSocketServices webSocketService) {
        this.s3Repository = s3Repository;
        this.albumRepository = albumRepository;
        this.webSocketService = webSocketService;
    }

    public String uploadAlbum(MultipartFile[] images, String albumName) throws IOException {

        List<String> listOfUrls = new ArrayList<String>();

        for (MultipartFile image : images) {
            String key = albumName + "/" + UUID.randomUUID().toString();

            s3Repository.uploadImage(key, image);

            String pictureUrl = s3Repository.getImageUrl(key);

            listOfUrls.add(pictureUrl);
        }

        Album newAlbum = albumRepository.saveAlbum(listOfUrls, albumName);
        webSocketService.broadcastNewAlbum(newAlbum);

        return "Album created successfully !";

    }
}
