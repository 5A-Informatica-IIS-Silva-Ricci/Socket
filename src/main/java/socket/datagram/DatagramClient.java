package datagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class DatagramClient {
    private static final int porta = 1025;
    private static final int maxlength = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();

            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            String testo = tastiera.readLine();
            byte[] buffer = testo.getBytes(StandardCharsets.UTF_8);

            InetAddress indirizzoServer = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, indirizzoServer, porta);

            ds.send(packet);

            byte[] bufferResponse = new byte[maxlength];
            DatagramPacket response = new DatagramPacket(bufferResponse, bufferResponse.length);

            ds.receive(response);
            System.out.println(new String(response.getData()));

            ds.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
