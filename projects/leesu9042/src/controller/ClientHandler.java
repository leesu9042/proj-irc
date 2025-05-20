package controller;

import dto.User;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final User user;

    public ClientHandler(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("서버에 연결되었습니다.");

            String msg;

            while ((msg = in.readLine()) != null) {
                if ("bye".equalsIgnoreCase(msg)) break;


                out.println("서버: " + msg);
            }

            System.out.println("클라이언트 연결 종료됨.");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
