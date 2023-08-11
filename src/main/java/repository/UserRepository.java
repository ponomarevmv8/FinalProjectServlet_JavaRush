package repository;

import entity.User;

public interface UserRepository {
    User findByUsername(String username);

    void save(User user);

    void update(User user);
}
