package dev.MSpilari.webSocketBackend.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.MSpilari.webSocketBackend.services.AlbumServices;

@RestController
@RequestMapping("/api/v1/")
public class AlbumController {
    private AlbumServices albumServices;

    public AlbumController(AlbumServices albumServices) {
        this.albumServices = albumServices;
    }

    @PostMapping("upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPictures(@RequestParam("images") MultipartFile[] images,
            @RequestParam("album_name") String albumName) throws IOException {

        return albumServices.uploadAlbum(images, albumName);

    }

}
