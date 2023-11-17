package entity;

import java.util.Map;

public class User {
    private String login;
    private Map<Integer, Integer> endings;

    public User() {
    }

    public User(String login, Map<Integer, Integer> endings) {
        this.login = login;
        this.endings = endings;
    }

    public void setEndings(Map<Integer, Integer> number) {
        this.endings = number;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Map<Integer, Integer> getEndings() {
        return endings;
    }
}
