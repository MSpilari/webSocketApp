import { CompatClient, Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { Album } from "./AlbumListTypes";

const createClient = (webSocketUrl: string) => {
  const socket = new SockJS(webSocketUrl); // URL do endpoint que você definiu no back
  const client = Stomp.over(socket);
  client.debug = () => {}; // desativa logs

  return client;
};

const connectClient = (
  client: CompatClient,
  destinationSubscriber: string,
  destinationSender: string,
  stateChanger: React.Dispatch<React.SetStateAction<Album[]>>
) => {
  client.connect({}, () => {
    client.subscribe(destinationSubscriber, (message) => {
      const receivedAlbums: Album[] = JSON.parse(message.body);
      Array.isArray(receivedAlbums)
        ? stateChanger((prev) => [...prev, ...receivedAlbums])
        : stateChanger((prev) => [...prev, receivedAlbums]);
    });

    // Se quiser requisitar todos os álbuns já existentes:
    client.send(destinationSender);
  });
};

export { createClient, connectClient };
