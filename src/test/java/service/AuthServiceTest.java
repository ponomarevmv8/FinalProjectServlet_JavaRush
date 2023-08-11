package service;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserRepository;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    private AuthService authService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        Class<?> authServiceClass = AuthServiceImpl.class;

        Constructor<?> constructor = authServiceClass.getDeclaredConstructor(UserRepository.class);
        constructor.setAccessible(true);

        this.authService = (AuthService) constructor.newInstance(userRepository);
    }

    @Test
    public void testLoginExistingUser() throws Exception {
        String testName = "Max";
        User user = new User();
        when(userRepository.findByUsername(testName)).thenReturn(user);

        User resultUser = authService.login(testName);
        verify(userRepository, never()).save(any(User.class));
        verify(userRepository).findByUsername(testName);

        assertEquals(user, resultUser);

    }

    @Test
    public void testLoginNotExistingUser() throws Exception {
        String testName = "Max";

        when(userRepository.findByUsername(testName)).thenReturn(null);
        doNothing().when(userRepository).save(any(User.class));

        User resultUser = authService.login(testName);

        verify(userRepository).findByUsername(testName);
        verify(userRepository).save(any(User.class));
        assertEquals(testName, resultUser.getLogin());
    }
}
