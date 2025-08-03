package ru.job4j.ood.lsp.user;

public class User {
    private final String username;
    private final String email;

    public User(String username, String email, ValidateData validator) {
        validator.validateUsername(username);
        validator.validateEmail(email);
        this.username = username.toLowerCase();
        this.email = email.toLowerCase();
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
