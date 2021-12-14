package stream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamServer {
    public static void main(String[] args) throws IOException {
        // Creo il socket per il server
        ServerSocket ss = new ServerSocket(1025);
        // Abilito le richieste
        Socket ds = ss.accept();

        // Aspetto una richiesta
        int n = ds.getInputStream().read();
        System.out.println("Richiesto il quadrato di " + n);
        // Creo la risposta
        int square = n*n;
        // Invio la risposta
        ds.getOutputStream().write(square);

        // Chiudo il socket
        ss.close();
    }
}
