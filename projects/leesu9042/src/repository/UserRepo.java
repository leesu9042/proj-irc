package repository;

import dto.User;

public interface UserRepo {
    void save(User user);
    User findById(String id);
    void delete(String id);

}
