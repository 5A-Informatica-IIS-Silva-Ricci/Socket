package esercizi.giocodellabomba;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    ServerSocket socket;
    Socket dataSocket;

    int timeout;

    public Server() throws IOException {
        socket = new ServerSocket(Gestore.portaServer);
        timeout = 100;
    }

    @Override
    public void run() {
        try {
            super.run();

            System.out.println("[S] - Server in attesa di connessioni");
            dataSocket = socket.accept();

            System.out.println("[S] - Server ha instaurato una connessione con un client, inizia il gioco");
            iniziaGioco();
        } catch (IOException e) {
            System.out.println("È avvenuto un errore nel server");
        }
    }

    private void iniziaGioco() {
        try {
            dataSocket.getOutputStream().write(timeout);
            while (true) {
                timeout = dataSocket.getInputStream().read();
                if (timeout <= 0) {
                    System.out.println("[S] - Il server ha vinto!");
                    break;
                } else {
                    timeout = timeout - Gestore.randomNumber(1, 20);
                    if (timeout <= 0) {
                        dataSocket.getOutputStream().write(0);
                        System.out.println("[S] - Il server è rimasto con la bomba in mano");
                        break;
                    } else {
                        dataSocket.getOutputStream().write(timeout);
                        System.out.println("[S] - Il Server ha passato la bomba al Client, timeout: " + timeout);
                    }
                }
            }

            dataSocket.close();
        } catch (IOException e) {
            System.out.println("[S] - È avvenuto un errore durante lo svolgimento del gioco:\n" + e);
        }
    }
}
