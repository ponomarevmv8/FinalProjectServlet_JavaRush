package repository;

import entity.Question;

public interface QuestionRepository {
    Question findById(int id);
}
