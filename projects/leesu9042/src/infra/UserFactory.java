package infra;

import dto.User;

import java.util.UUID;
import java.util.Random;

public class UserFactory {

    public User createRandomUser() {
        String id = UUID.randomUUID().toString();

        String nickname = "user" + new Random().nextInt(10000);

        return new User(id, nickname);
    }
}