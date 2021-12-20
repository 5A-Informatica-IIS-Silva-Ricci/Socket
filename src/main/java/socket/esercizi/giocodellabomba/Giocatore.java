package esercizi.giocodellabomba;

import java.io.IOException;
import java.net.Socket;

public abstract class Giocatore {
    private final String nomeGiocatore;
    private final boolean inizia;

    private int timeout = 100;

    public Giocatore(String nomeGiocatore, boolean inizia) {
        this.nomeGiocatore = nomeGiocatore;
        this.inizia = inizia;
    }

    protected void iniziaGioco(Socket dataSocket) {
        try {
            // Se il giocatore deve iniziare passa per primo la bomba con il timeout di 100
            if (inizia) {
                System.out.println(nomeGiocatore + " ha passato la bomba, timeout: " + timeout);
                dataSocket.getOutputStream().write(timeout);
            }

            while (true) {
                // Aspetto la bomba con il timeout
                timeout = dataSocket.getInputStream().read();
                // Se minore di 0 vuol dire che l'altro giocatore ha perso
                if (timeout <= 0) {
                    System.out.println(nomeGiocatore + " ha vinto!");
                    break;
                } else {
                    // Altrimenti diminuisco il timeout
                    timeout -= Gioco.numeroRandom(1, 20);
                    // Se lo diminuisco fino ad arrivare a zero il giocatore ha perso
                    if (timeout <= 0) {
                        System.out.println(nomeGiocatore + " è rimasto con la bomba in mano");
                        dataSocket.getOutputStream().write(0);
                        break;
                    } else {
                        System.out.println(nomeGiocatore + " ha passato la bomba, timeout: " + timeout);
                        dataSocket.getOutputStream().write(timeout);
                    }
                }
            }

            dataSocket.close();
        } catch (IOException e) {
            System.out.println(nomeGiocatore + " - È avvenuto un errore durante lo svolgimento del gioco:\n" + e);
        }
    }
}
