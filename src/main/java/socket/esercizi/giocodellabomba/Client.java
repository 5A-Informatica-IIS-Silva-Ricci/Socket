package esercizi.giocodellabomba;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Giocatore implements Runnable {
    public Client() {
        // Inizializzo la classe Giocatore
        super("Client", false);
    }

    // Quando il thread viene startato viene eseguito il metodo Run
    @Override
    public void run() {
        try {
            System.out.println("Client - sta instaurando una connessione al server");
            Socket dataSocket = new Socket(InetAddress.getLocalHost(), Gioco.portaServer);

            super.iniziaGioco(dataSocket);
        } catch (IOException e) {
            System.out.println("Client - è avvenuto un errore nell'inizializzazione: " + e);
        }
    }
}
