package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Question;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {


    private static QuestionRepository questionRepository;
    private ObjectMapper om = new ObjectMapper();
    private File file = new File(this.getClass().getClassLoader().getResource("question.json").getFile());

    private QuestionRepositoryImpl() {
    }

    public static QuestionRepository getQuestionRepository() {
        if (questionRepository == null) {
            questionRepository = new QuestionRepositoryImpl();
        }
        return questionRepository;
    }

    @Override
    public Question findById(int id) {
        try {
            List<Question> questions = Arrays.asList(om.readValue(file, Question[].class));
            for (Question question : questions) {
                if (question.getId() == id) {
                    return question;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
