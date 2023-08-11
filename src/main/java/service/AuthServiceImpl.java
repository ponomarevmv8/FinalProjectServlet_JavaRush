package service;

import entity.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.HashMap;

public class AuthServiceImpl implements AuthService {

    private static AuthService authService;
    private UserRepository userRepository;

    private AuthServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public static AuthService getAuthService(){
        if(authService == null) {
            authService = new AuthServiceImpl(UserRepositoryImpl.getUserRepository());
        }
        return authService;
    }

    @Override
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
