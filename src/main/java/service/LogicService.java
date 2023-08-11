package service;

import entity.Question;
import entity.User;

public interface LogicService {
    Question getQuestion(int id);

    void end(User user, Question question);
}
