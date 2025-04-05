package dev.MSpilari.webSocketBackend.models;

import java.util.List;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Album {

    private String albumId;
    private String albumName;
    private List<String> picturesUrl;

    public Album() {
    }

    public Album(String albumId, String albumName, List<String> picturesUrl) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.picturesUrl = picturesUrl;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("album_id")
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @DynamoDbAttribute("album_name")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @DynamoDbAttribute("pictures_url")
    public List<String> getPicturesUrl() {
        return picturesUrl;
    }

    public void setPicturesUrl(List<String> picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    @Override
    public String toString() {
        return "Album [albumId=" + albumId + ", albumName=" + albumName + ", picturesUrl=" + picturesUrl + "]";
    }
}
