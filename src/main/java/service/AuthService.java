package service;

import entity.User;
import repository.UserRepository;

import java.util.HashMap;

public class AuthService {

    private static AuthService authService;
    private UserRepository userRepository;

    private AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static AuthService getAuthService(){
        if(authService == null) {
            authService = new AuthService(UserRepository.getUserRepository());
        }
        return authService;
    }

    public User login(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            User userCreated = new User();
            userCreated.setLogin(username);
            userCreated.setEndings(new HashMap<>());
            userRepository.save(userCreated);
            return userCreated;
        }
        return user;
    }

}
