package service;


import controller.ClientHandler;
import dto.User;
import infra.ClientHandlerFactory;
import infra.ClientSocketFactory;
import repository.ClientHandlerManager;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;




import controller.ClientHandler;
import dto.User;
import infra.ClientHandlerFactory;
import repository.ClientHandlerManager;

import java.net.Socket;

public class ClientHandlerService {

    private final ClientHandlerFactory handlerFactory;
    private final ClientHandlerManager handlerManager;

    public ClientHandlerService(ClientHandlerFactory handlerFactory, ClientHandlerManager handlerManager) {
        this.handlerFactory = handlerFactory;
        this.handlerManager = handlerManager;
    }

    public ClientHandler createAndRegisterHandler(Socket socket, User user) {
        ClientHandler handler = handlerFactory.create(socket, user);
        handlerManager.register(user.getId(), handler);
        return handler;
    }

    // 기존 메시지 처리 메서드들은 그대로 유지
}


