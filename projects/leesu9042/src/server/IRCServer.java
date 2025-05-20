package server;

import service.ClientConnectionService;

public class IRCServer {

    private final ClientConnectionService clientConnectionService;

    public IRCServer(ClientConnectionService clientConnectionService) {
        this.clientConnectionService = clientConnectionService;
    }

    public void start() {
        System.out.println("IRC 서버 실행 중... 클라이언트 접속 대기");

        while (true) {
            try {
                clientConnectionService.acceptAndStartClient(); // 1명의 클라이언트 처리
            } catch (Exception e) {
                System.err.println("클라이언트 처리 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
                // 무한 루프지만 심각한 예외라면 종료할 수도 있음
                // break;
            }
        }
    }
}
