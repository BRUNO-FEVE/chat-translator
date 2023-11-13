package Interfaces;

public class User {
    public String name;
    public String email;
    public String password;
    public String phoneNumber;
    public String language;

    public User(String name, String email, String password, String phoneNumber, String language) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.language = language;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
