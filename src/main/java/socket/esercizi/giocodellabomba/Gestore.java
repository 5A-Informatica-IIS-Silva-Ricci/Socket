package esercizi.giocodellabomba;

import java.util.concurrent.ThreadLocalRandom;

public class Gestore {
    public static final int portaServer = 1025;
    public static void main(String[] args) {
        try {
            Server server = new Server();
            Client client = new Client();

            server.start();
            client.start();

            server.join();
            client.join();

            System.out.println("[S] - Gioco terminato");
        } catch (Exception e) {
            System.out.println("È avvenuto un errore: " + e);
        }
    }

    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
