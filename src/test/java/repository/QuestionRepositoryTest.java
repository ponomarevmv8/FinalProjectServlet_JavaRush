package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Question;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class QuestionRepositoryTest {

    @Mock
    private ObjectMapper om;

    @Mock
    private File file;

    private QuestionRepository questionRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        Class<?> questionRepositoryClass = QuestionRepositoryImpl.class;
        Constructor<?> constructor = questionRepositoryClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.questionRepository = (QuestionRepository) constructor.newInstance();
        Field omField = questionRepositoryClass.getDeclaredField("om");
        omField.setAccessible(true);
        omField.set(questionRepository, this.om);
        Field fileField = questionRepositoryClass.getDeclaredField("file");
        fileField.setAccessible(true);
        fileField.set(questionRepository, this.file);
    }


    @Test
    public void testFindExistsById() throws Exception {
        int testId = 1;
        Question testQuestion = new Question();
        testQuestion.setId(testId);
        Question[] testQuestions = new Question[]{testQuestion};

        when(om.readValue(file, Question[].class)).thenReturn(testQuestions);

        Question resultQuestion = questionRepository.findById(testId);

        assertEquals(testId, resultQuestion.getId());
    }

    @Test
    public void testFindNotExistsById() throws Exception {
        int testId = 1;
        Question testQuestion = new Question();
        testQuestion.setId(2);
        Question[] testQuestions = new Question[]{testQuestion};

        when(om.readValue(file, Question[].class)).thenReturn(testQuestions);

        Question resultQuestion = questionRepository.findById(testId);

        assertNull(resultQuestion);
    }

}
