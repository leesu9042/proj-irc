import application.IRCApplication;
import server.IRCServer;

public class Main {
    public static void main(String[] args) {
        try {
            IRCApplication app = new IRCApplication();
            IRCServer server = app.configureServer();  // 모든 구성된 IRCServer 반환

            server.start(); // 서버 실행
        } catch (Exception e) {
            System.err.println("서버 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}