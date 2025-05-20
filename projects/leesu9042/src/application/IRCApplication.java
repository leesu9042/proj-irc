package application;
import dto.User;
import infra.ClientHandlerFactory;
import infra.ClientSocketFactory;
import infra.UserFactory;
import repository.ClientHandlerManager;
import repository.InMemoryUserRepo;
import repository.UserRepo;
import server.IRCServer;
import service.ClientConnectionService;
import service.ClientHandlerService;
import service.UserService;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//객체 조립
public class IRCApplication {

    public IRCServer configureServer() throws IOException {
        // 1. 유저 관련 구성
        UserRepo userRepo = new InMemoryUserRepo();
        UserFactory userFactory = new UserFactory();
        UserService userService = new UserService(userFactory, userRepo);

        // 2. 핸들러 구성
        ClientHandlerManager handlerManager = new ClientHandlerManager();
        ClientHandlerFactory handlerFactory = new ClientHandlerFactory();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ClientSocketFactory socketFactory = new ClientSocketFactory(5000);

        // 3. 서비스 조합
        ClientHandlerService handlerService = new ClientHandlerService(handlerFactory, handlerManager);
        ClientConnectionService connectionService = new ClientConnectionService(
                socketFactory, executor, userService, handlerService
        );

        // 4. 서버 조합
        return new IRCServer(connectionService);
    }
}
