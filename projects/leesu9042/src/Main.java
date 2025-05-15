import infra.ClientSocketFactory;

import java.io.IOException;

public static void main(String args[]) throws IOException {

    int threadPoolSize = 10;
    ClientSocketFactory socketFactory = new ClientSocketFactory(5000);
    IRCServer ircServer = new IRCServer(socketFactory,threadPoolSize);

    ircServer.start();



}



