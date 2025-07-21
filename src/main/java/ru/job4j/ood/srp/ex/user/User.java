package ru.job4j.ood.srp.ex.user;

public class User {
    private String username;
    private String email;

    DataValidation dataValidation = new DataValidation();

    public User(String username, String email) {
        dataValidation.validateEmail(email);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
