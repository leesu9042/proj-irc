package infra;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


//소켓 생성해서 건네주는 클래스

public class ClientSocketFactory {
    private final ServerSocket serverSocket;

    public ClientSocketFactory(int port) throws IOException {
        InetAddress localOnly = InetAddress.getByName("127.0.0.1");

        this.serverSocket = new ServerSocket(port, 0, localOnly);

        System.out.println("SocketFactory가 127.0.0.1:" + port + " 에 바인딩됨");
    }

    public Socket waitForClient() throws IOException {
        return serverSocket.accept();
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
