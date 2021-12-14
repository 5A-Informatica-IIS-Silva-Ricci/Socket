package datagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class DatagramClient {
    public static void main(String[] args) {
        try {
            // Creo il socket datagram
            DatagramSocket ds = new DatagramSocket();

            // Prendo in input da tastiera del testo
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            String testo = tastiera.readLine();
            // Creo il buffer con i byte del testo
            byte[] buffer = testo.getBytes(StandardCharsets.UTF_8);

            // Creo il pacchetto datagram
            InetAddress indirizzoServer = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, indirizzoServer, ImpostazioniConnessione.portaServer);

            // Invio il pacchetto
            ds.send(packet);

            // Preparo il pacchetto che dovrà contenere la risposta ricevuta dal server
            byte[] bufferResponse = new byte[ImpostazioniConnessione.lunghezzaMaxPacchetti];
            DatagramPacket response = new DatagramPacket(bufferResponse, bufferResponse.length);

            // Aspetto la risposta
            ds.receive(response);
            // La visualizzo
            System.out.println(new String(response.getData()).trim());

            // Chiudo il socket
            ds.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
