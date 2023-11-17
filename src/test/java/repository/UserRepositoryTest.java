package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {

    @Mock
    private ObjectMapper om;

    @Mock
    private File file;

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        Class<?> userRepositoryClass = UserRepositoryImpl.class;
        Constructor<?> constructor = userRepositoryClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.userRepository = (UserRepository) constructor.newInstance();
        Field omField = userRepositoryClass.getDeclaredField("om");
        omField.setAccessible(true);
        omField.set(userRepository, this.om);
        Field fileField = userRepositoryClass.getDeclaredField("file");
        fileField.setAccessible(true);
        fileField.set(userRepository, this.file);
    }


    @Test
    public void testFindByUsernameExistsUsername() throws Exception {
        String testUsername = "Max";
        User[] testUsers = new User[] {new User(testUsername, new HashMap<>())};

        when(om.readValue(this.file, User[].class)).thenReturn(testUsers);

        User resultUser = userRepository.findByUsername(testUsername);

        assertEquals(testUsername, resultUser.getLogin());
    }

    @Test
    public void testFindByUsernameNotExistsUsername() throws Exception {
        String testUsername = "Max";
        User[] testUsers = new User[] {new User("Bob", new HashMap<>())};

        when(om.readValue(this.file, User[].class)).thenReturn(testUsers);

        User resultUser = userRepository.findByUsername(testUsername);

        assertNull(resultUser);
    }


}
