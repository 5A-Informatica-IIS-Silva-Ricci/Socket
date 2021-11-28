package stream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1025);
        Socket ds = ss.accept();

        int n = ds.getInputStream().read();
        int square = n*n;
        ds.getOutputStream().write(square);

        ss.close();
    }
}
