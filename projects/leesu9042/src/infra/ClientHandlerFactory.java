package infra;

import controller.ClientHandler;
import dto.User;

import java.net.Socket;

public class ClientHandlerFactory {
    public ClientHandler create(Socket socket, User user) {
        return new ClientHandler(socket,user);
    }
}

