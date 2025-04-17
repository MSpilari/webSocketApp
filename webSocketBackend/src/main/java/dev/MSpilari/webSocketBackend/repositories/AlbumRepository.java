package dev.MSpilari.webSocketBackend.repositories;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import dev.MSpilari.webSocketBackend.models.Album;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class AlbumRepository {

    private final DynamoDbTable<Album> albumTable;

    public AlbumRepository(DynamoDbEnhancedClient enhancedClient,
            @Value("${spring.cloud.aws.dynamodb.table-name}") String tableName) {
        this.albumTable = enhancedClient.table(tableName, TableSchema.fromBean(Album.class));
    }

    public Album saveAlbum(List<String> picturesUrl, String albumName) {
        String albumId = generateAlbumId();

        Album newAlbum = new Album(albumId, albumName, picturesUrl);

        albumTable.putItem(newAlbum);

        return newAlbum;
    }

    private String generateAlbumId() {
        Instant now = Instant.now();
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
                .withZone(ZoneOffset.UTC)
                .format(now);
    }

    public Optional<Album> getAlbumById(String albumId) {
        return Optional.ofNullable(albumTable.getItem(r -> r.key(k -> k.partitionValue(albumId))));
    }

    public List<Album> getAllAlbums() {
        return albumTable.scan().items().stream().toList();
    }
}
