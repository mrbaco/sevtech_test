package models;

public class UserData {

    public String login;
    public String password;

    public UserData setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserData setPassword(String password) {
        this.password = password;
        return this;
    }

}
