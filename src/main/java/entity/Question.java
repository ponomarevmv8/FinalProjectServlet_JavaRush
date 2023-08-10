package entity;

import java.util.List;
import java.util.Map;

public class Question {

    private int id;
    private List<String> questions;

    private Map<Integer, String> answers;

    public Question() {
    }

    public Question(int id, List<String> questions, Map<Integer, String> answers) {
        this.id = id;
        this.questions = questions;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public Map<Integer, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, String> answers) {
        this.answers = answers;
    }
}
