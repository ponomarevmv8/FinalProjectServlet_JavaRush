package service;

import entity.User;

public interface AuthService {
    User login(String username);
}
