package service;

import entity.Question;
import entity.User;
import repository.QuestionRepository;
import repository.QuestionRepositoryImpl;
import repository.UserRepositoryImpl;

import java.util.Map;

public class LogicServiceImpl implements LogicService {

    private static LogicService logicService;
    private QuestionRepository questionRepository;

    private LogicServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public static LogicService getLogicService() {
        if (logicService == null) {
            logicService = new LogicServiceImpl(QuestionRepositoryImpl.getQuestionRepository());
        }
        return logicService;
    }

    @Override
    public Question getQuestion(int id) {
        Question question = questionRepository.findById(id);
        return question;
    }

    @Override
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
        UserRepositoryImpl.getUserRepository().update(user);
    }
}
