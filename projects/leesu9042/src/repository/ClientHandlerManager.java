//User clientHandler Mapping
package repository;

import controller.ClientHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandlerManager {
    private final Map<String, ClientHandler> handlerMap = new ConcurrentHashMap<>();


    public void register(String userId, ClientHandler handler) {
        handlerMap.put(userId, handler);
    }

    public ClientHandler get(String userId) {
        return handlerMap.get(userId);
    }
}
