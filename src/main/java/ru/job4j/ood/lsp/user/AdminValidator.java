package ru.job4j.ood.lsp.user;

public class AdminValidator implements ValidateData {
    @Override
    public void validateUsername(String username) {
        if (username == null || !username.startsWith("admin_")) {
            throw new IllegalArgumentException("Admin username must start with 'admin_'");
        }
    }
}
