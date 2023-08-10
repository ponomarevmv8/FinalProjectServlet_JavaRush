package repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static UserRepository userRepository;
    private ObjectMapper om = new ObjectMapper();
    private File file = new File(this.getClass().getClassLoader().getResource("users.json").getFile());

    private UserRepository() {

    }

    public static UserRepository getUserRepository() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public User findByUsername(String username) {

        try {
            List<User> users = Arrays.asList(om.readValue(file, User[].class));
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
        try {
            List<User> users = new ArrayList<> (Arrays.asList(om.readValue(file, User[].class)));
            users.add(user);
            om.writeValue(file, users);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try {
            List<User> users = Arrays.asList(om.readValue(file, User[].class));
            users = users.stream()
                            .filter(usr -> !usr.getLogin().equals(user.getLogin()))
                            .collect(Collectors.toList());
            users.add(user);
            om.writeValue(file, users);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
