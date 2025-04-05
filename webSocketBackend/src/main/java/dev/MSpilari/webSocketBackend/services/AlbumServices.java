package dev.MSpilari.webSocketBackend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.MSpilari.webSocketBackend.repositories.AlbumRepository;
import dev.MSpilari.webSocketBackend.repositories.S3Repository;

@Service
public class AlbumServices {

    private final S3Repository s3Repository;
    private final AlbumRepository albumRepository;

    public AlbumServices(S3Repository s3Repository, AlbumRepository albumRepository) {
        this.s3Repository = s3Repository;
        this.albumRepository = albumRepository;
    }

    public String uploadAlbum(MultipartFile[] images, String albumName) throws IOException {

        List<String> listOfUrls = new ArrayList<String>();

        for (MultipartFile image : images) {
            String key = albumName + "/" + UUID.randomUUID().toString();

            s3Repository.uploadImage(key, image);

            String pictureUrl = s3Repository.getImageUrl(key);

            listOfUrls.add(pictureUrl);
        }

        albumRepository.saveAlbum(listOfUrls, albumName);

        return "Album created successfully !";

    }
}
