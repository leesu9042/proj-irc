package service;

import dto.User;
import infra.UserFactory;
import repository.UserRepo;

public class UserService {

    private final UserFactory userFactory;
    private final UserRepo userRepo;

    public UserService(UserFactory userFactory, UserRepo userRepo) {
        this.userFactory = userFactory;
        this.userRepo = userRepo;

    }


    public User createAndSaveRandomUser() {
        User user = userFactory.createRandomUser();
        userRepo.save(user);
        return user;
    }


    public User findUserById(String id) {
        return userRepo.findById(id);
    }

    public void changeUsername(User user, String newName) {
        user.setUsername(newName);
    }
}
