package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository {

    private static UserRepository userRepository;

    private UserRepository() {

    }

    public static UserRepository getUserRepository() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public User findByUsername(String username) {
        ObjectMapper om = new ObjectMapper();
        File file = new File(this.getClass().getClassLoader().getResource("users.json").getFile());

        try {
            User[] usersArray = om.readValue(file, User[].class);
            List<User> users = Arrays.asList(usersArray);
            for(User user : users) {
                if(user.getLogin().equals(username)){
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка поиска юзера в файле");
            throw new RuntimeException(e);
        }
        return null;
    }

    public void save(User user) {
        ObjectMapper om = new ObjectMapper();
        File file = new File(this.getClass().getClassLoader().getResource("users.json").getFile());

        try {
            User[] usersArray = om.readValue(file, User[].class);
            List<User> users = new ArrayList<> (Arrays.asList(usersArray));
            users.add(user);
            om.writeValue(file, users);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            throw new RuntimeException(e);
        }
    }

}
