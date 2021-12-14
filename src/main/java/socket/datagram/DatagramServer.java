package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class DatagramServer {
    public static void main(String[] args) {
        try {
            // Creo il socket datagram
            DatagramSocket ds = new DatagramSocket(ImpostazioniConnessione.portaServer);

            // Creo il buffer ed il pacchetto datagram per la richiesta del client
            byte[] buffer = new byte[ImpostazioniConnessione.lunghezzaMaxPacchetti];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Aspetto un pacchetto
            ds.receive(packet);

            // Estrapolo il messaggio
            String messaggio = new String(packet.getData()).trim();

            // Invio la risposta con il messaggio in UpperCase
            String messaggioUppercase = messaggio.toUpperCase();
            DatagramPacket response = new DatagramPacket(messaggioUppercase.getBytes(StandardCharsets.UTF_8), messaggio.length(), packet.getAddress(), packet.getPort());
            ds.send(response);

            // Chiudo il socket
            ds.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
