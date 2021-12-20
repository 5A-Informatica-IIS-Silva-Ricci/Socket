package esercizi.giocodellabomba;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Giocatore implements Runnable {
    public Server() {
        // Inizializzo la classe Giocatore
        super("Server", true);
    }

    // Quando il thread viene startato viene eseguito il metodo Run
    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(Gioco.portaServer);

            System.out.println("Server - in attesa di connessioni");
            Socket dataSocket = socket.accept();

            System.out.println("Server - ha instaurato una connessione con un client, inizia il gioco");
            super.iniziaGioco(dataSocket);
        } catch (IOException e) {
            System.out.println("Server - è avvenuto un errore nell'inizializzazione: " + e);
        }
    }
}
