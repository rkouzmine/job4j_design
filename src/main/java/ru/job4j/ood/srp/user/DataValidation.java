package ru.job4j.ood.srp.user;

public class DataValidation implements Validate<User> {

    @Override
    public void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w+.-]+@[\\w.-]+\\.[a-zA-Z]{2,7}$")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }

}
