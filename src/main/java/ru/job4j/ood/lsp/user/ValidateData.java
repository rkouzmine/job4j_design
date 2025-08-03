package ru.job4j.ood.lsp.user;

public interface ValidateData {

    void validateUsername(String username);

    default void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w+.-]+@[\\w.-]+\\.[a-zA-Z]{2,7}$")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

}
