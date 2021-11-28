package stream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class StreamClient {
    public static void main(String[] args) throws IOException {
        Socket cs = new Socket(InetAddress.getLocalHost(), 1025);

        cs.getOutputStream().write(4);
        int square = cs.getInputStream().read();
        System.out.println(square);

        cs.close();
    }
}
