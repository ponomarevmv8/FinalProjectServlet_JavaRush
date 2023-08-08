package entity;

public class User {
    private String login;
    private int number;

    public User() {
    }

    public User(String login, int number) {
        this.login = login;
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public int getNumber() {
        return number;
    }
}
