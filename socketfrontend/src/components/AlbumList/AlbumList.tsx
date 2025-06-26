import React, { useEffect } from "react";
import { useAlbumListHooks } from "./AlbumListHooks";
import { connectClient, createClient } from "./AlbumListService";

import "./styles.css";

const AlbumList: React.FC = () => {
  const { albums, setAlbums } = useAlbumListHooks();

  useEffect(() => {
    const client = createClient(`${import.meta.env.VITE_API_BACKEND_URL}/ws`);
    connectClient(client, "/topic/albums", "/app/albums", setAlbums);

    return () => {
      client.disconnect(() => {
        console.log("WebSocket desconectado no cleanup");
      });
    };
  }, []);

  return (
    <div>
      <h2>Álbuns</h2>

      <ul className="albumUList">
        {albums.map((album) => (
          <li key={album.albumId} className="listItem">
            <p>{album.albumName}</p>
            {album.picturesUrl.map((url, index) => (
              <img
                key={index}
                src={url}
                alt={`Foto ${index}`}
                className="imageAlbum"
              />
            ))}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AlbumList;
