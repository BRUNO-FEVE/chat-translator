package Interfaces;

public class User {
    public String name;
    public String phoneNumber;
    public String language;
    public String picturePath;

    public User(String name, String phoneNumber, String language, String picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.picturePath = picture;
    }

}
