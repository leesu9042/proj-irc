package repository;

import dto.User;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepo implements UserRepo {
    private final Map<String, User> store = new HashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return store.get(id);
    }

    @Override
    public void delete(String id) {
        store.remove(id); // ✅ 구현 완료


    }
}
