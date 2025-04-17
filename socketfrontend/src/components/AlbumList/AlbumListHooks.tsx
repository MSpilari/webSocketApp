import { useState } from "react";
import { Album } from "./AlbumListTypes";
import { CompatClient } from "@stomp/stompjs";

const useAlbumListHooks = () => {
  const [albums, setAlbums] = useState<Album[]>([]);
  const [stompClient, setStompClient] = useState<CompatClient | null>(null);

  return { albums, setAlbums, stompClient, setStompClient };
};

export { useAlbumListHooks };
