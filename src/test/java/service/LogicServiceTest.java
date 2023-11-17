package service;

import entity.Question;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import repository.QuestionRepository;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LogicServiceTest {

    private LogicService logicService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Question question;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        Class<?> logicService = LogicServiceImpl.class;
        Constructor<?> constructor = logicService.getDeclaredConstructor(QuestionRepository.class);
        constructor.setAccessible(true);
        this.logicService = (LogicService) constructor.newInstance(questionRepository);
    }

    @Test
    public void testGetQuestion() throws Exception {
        int testId = 1;
        Question question = new Question();
        when(questionRepository.findById(testId)).thenReturn(question);

        Question resultQuestion = logicService.getQuestion(testId);

        assertEquals(question, resultQuestion);
    }

    @Test
    public void testEndExistingEndings() {
        try(MockedStatic<UserRepositoryImpl> userRepositoryMockedStatic = mockStatic(UserRepositoryImpl.class)) {
            Map<Integer, Integer> testEndings = new HashMap<>();
            testEndings.put(1, 2);
            int testId = 1;

            when(user.getEndings()).thenReturn(testEndings);
            when(question.getId()).thenReturn(testId);
            userRepositoryMockedStatic.when(UserRepositoryImpl::getUserRepository).thenReturn(userRepository);
            doNothing().when(user).setEndings(anyMap());

            logicService.end(user, question);

            assertEquals(3, testEndings.get(testId));
            assertEquals(1, testEndings.keySet().size());
        }

    }

    @Test
    public void testEndNotExistingEndings() {
        try(MockedStatic<UserRepositoryImpl> userRepositoryMockedStatic = mockStatic(UserRepositoryImpl.class)) {
            Map<Integer, Integer> testEndings = new HashMap<>();
            testEndings.put(1, 2);
            int testId = 2;

            when(user.getEndings()).thenReturn(testEndings);
            when(question.getId()).thenReturn(testId);
            userRepositoryMockedStatic.when(UserRepositoryImpl::getUserRepository).thenReturn(userRepository);
            doNothing().when(user).setEndings(anyMap());

            logicService.end(user, question);

            assertEquals(1, testEndings.get(testId));
            assertEquals(2, testEndings.keySet().size());
        }

    }
}
