package stream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class StreamClient {
    public static void main(String[] args) throws IOException {
        // Creo il socket
        Socket cs = new Socket(InetAddress.getLocalHost(), 1025);

        // Invio "4" al server
        cs.getOutputStream().write(4);
        // Aspetto la risposta
        int square = cs.getInputStream().read();
        // Visualizzo la risposta
        System.out.println(square);

        // Chiudo il socket
        cs.close();
    }
}
