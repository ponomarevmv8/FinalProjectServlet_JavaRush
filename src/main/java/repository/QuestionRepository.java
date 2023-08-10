package repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QuestionRepository {


    private static QuestionRepository questionRepository;

    private QuestionRepository(){}

    public static QuestionRepository getQuestionRepository(){
        if(questionRepository == null) {
            questionRepository = new QuestionRepository();
        }
        return questionRepository;
    }

    public Question findById(int id) {
        ObjectMapper om = new ObjectMapper();
        File file = new File(this.getClass().getClassLoader().getResource("question.json").getFile());

        try {
            List<Question> questions = Arrays.asList(om.readValue(file, Question[].class));
            for(Question question : questions) {
                if(question.getId() == id) {
                    return question;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            JsonNode node = om.readTree(file);
//            for (JsonNode questionNode : node){
//                if(id == questionNode.get("id").asInt()) {
//                    Question questionReturn  = om.convertValue(questionNode, Question.class);
//                    return questionReturn;
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
