import controller.ClientHandler;
import infra.ClientSocketFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IRCServer {
    private final ClientSocketFactory socketFactory;
    private final ExecutorService executor;

    public IRCServer(ClientSocketFactory socketFactory, int threadPoolSize) {
        this.socketFactory = socketFactory;
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void start() {
        System.out.println("IRCServer 실행 중...");
        while (true) {
            try {

                Socket clientSocket = socketFactory.waitForClient();

                executor.submit(new ClientHandler(clientSocket));

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        shutdown();
    }

    public void shutdown() {
        executor.shutdown();
        try {
            socketFactory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
