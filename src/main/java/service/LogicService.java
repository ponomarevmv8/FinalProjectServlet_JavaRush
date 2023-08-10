package service;

import entity.Question;
import entity.User;
import repository.QuestionRepository;
import repository.UserRepository;

import java.util.Map;

public class LogicService {

    private static LogicService logicService;
    private QuestionRepository questionRepository;

    private LogicService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public static LogicService getLogicService() {
        if (logicService == null) {
            logicService = new LogicService(QuestionRepository.getQuestionRepository());
        }
        return logicService;
    }

    public Question getQuestion(int id) {
        Question question = questionRepository.findById(id);
        return question;
    }

    public void end(User user, Question question) {
        Map<Integer, Integer> endingsUser = user.getEndings();
        int idQuestion = question.getId();
        boolean isAdd = true;
        for (Integer id : endingsUser.keySet()) {
            if (id == idQuestion) {
                endingsUser.put(id, endingsUser.get(id) + 1);
                isAdd = false;
            }
        }
        if (isAdd) {
            endingsUser.put(idQuestion, 1);
        }
        user.setEndings(endingsUser);
        UserRepository.getUserRepository().update(user);
    }
}
