package ru.job4j.ood.lsp.user;

public class UserValidator implements ValidateData {
    @Override
    public void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username can't be empty");
        }
    }
}
