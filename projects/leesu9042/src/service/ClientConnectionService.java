package service;

import controller.ClientHandler;
import dto.User;
import infra.ClientSocketFactory;
import repository.ClientHandlerManager;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ClientConnectionService {

    private final ClientSocketFactory socketFactory;
    private final ExecutorService executor;
    private final UserService userService;
    private final ClientHandlerService handlerService;

    public ClientConnectionService(
            ClientSocketFactory socketFactory,
            ExecutorService executor,
            UserService userService,
            ClientHandlerService handlerService
    ) {
        this.socketFactory = socketFactory;
        this.executor = executor;
        this.userService = userService;
        this.handlerService = handlerService;
    }

    /**
     * 클라이언트 연결을 수락하고, 유저를 생성한 후,
     * 핸들러를 등록하고 실행하는 전체 흐름 처리
     */
    public void acceptAndStartClient() throws IOException {
        // 1. 클라이언트 소켓 수락
        Socket socket = socketFactory.waitForClient();

        // 2. 랜덤 유저 생성 및 저장
        User user = userService.createAndSaveRandomUser();

        // 3. 핸들러 생성 + 등록 (위임)
        ClientHandler handler = handlerService.createAndRegisterHandler(socket, user);

        // 4. 핸들러 실행 (멀티스레드)
        executor.submit(handler);
    }
}
