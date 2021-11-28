package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServer {
    private static final int porta = 1025;
    private static final int maxLength = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(porta);

            byte[] buffer = new byte[maxLength];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            ds.receive(packet);
            String messaggio = new String(packet.getData());

            ds.close();

            System.out.println(messaggio);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
