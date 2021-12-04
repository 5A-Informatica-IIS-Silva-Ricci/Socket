package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class DatagramServer {
    private static final int porta = 1025;
    private static final int maxLength = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(porta);

            byte[] buffer = new byte[maxLength];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            ds.receive(packet);
            String messaggio = new String(packet.getData()).substring(0, packet.getLength());
            System.out.println(messaggio);

            DatagramPacket response = new DatagramPacket(messaggio.toUpperCase().getBytes(StandardCharsets.UTF_8), messaggio.length(), packet.getAddress(), packet.getPort());
            System.out.println("okkkkklessgo");
            ds.send(response);

            ds.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
