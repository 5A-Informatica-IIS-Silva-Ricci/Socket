package esercizi.giocodellabomba;

import java.util.concurrent.ThreadLocalRandom;

public class Gioco {
    // Porta del server socket
    public static final int portaServer = 1025;

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Client client = new Client();

            // Creo i thread per i duo giocatori (client e server) in quanto devono essere eseguiti "in contemporanea"
            Thread threadServer = new Thread(server);
            Thread threadClient = new Thread(client);

            // Faccio partire i giocatori
            threadServer.start();
            threadClient.start();

            // Attendo che terminino l'esecuzione
            threadServer.join();
            threadClient.join();

            System.out.println("Gioco terminato");
        } catch (Exception e) {
            System.out.println("È avvenuto un errore: " + e);
        }
    }

    // Metodo che ritorna un numero random tra minimo e massimo
    public static int numeroRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
