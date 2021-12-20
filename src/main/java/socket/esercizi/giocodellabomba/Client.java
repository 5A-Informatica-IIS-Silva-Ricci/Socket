package esercizi.giocodellabomba;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread {
    Socket dataSocket;
    int timeout;

    public Client() throws IOException {
        System.out.println("[C] - Il client sta instaurando una connessione al server");
        dataSocket = new Socket(InetAddress.getLocalHost(), Gestore.portaServer);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("[C] - Client ha instaurato una connessione con il server, inizia il gioco");
        iniziaGioco();
    }

    private void iniziaGioco() {
        try {
            while (true) {
                timeout = dataSocket.getInputStream().read();
                if (timeout <= 0) {
                    System.out.println("[C] - Il client ha vinto!");
                    break;
                } else {
                    timeout = timeout - Gestore.randomNumber(1, 20);
                    dataSocket.getOutputStream().write(timeout);
                    if (timeout <= 0) {
                        dataSocket.getOutputStream().write(0);
                        System.out.println("[C] - Il client è rimasto con la bomba in mano");
                        break;
                    } else {
                        dataSocket.getOutputStream().write(timeout);
                        System.out.println("[S] - Il Client ha passato la bomba al Server, timeout: " + timeout);
                    }
                }
            }

            dataSocket.close();
        } catch (IOException e) {
            System.out.println("[C] - È avvenuto un errore durante lo svolgimento del gioco:\n" + e);
        }
    }
}
