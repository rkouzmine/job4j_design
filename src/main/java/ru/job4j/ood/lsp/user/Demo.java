package ru.job4j.ood.lsp.user;

public class Demo {
    public static void main(String[] args) {
        UserValidator userValidator = new UserValidator();
        AdminValidator adminValidator = new AdminValidator();
        User user = new User("Alice", "Alice@gmail.com", userValidator);
        User admin = new User("admin_Alex", "Alex@gmail.ru", adminValidator);
        System.out.println(user);
        System.out.println(admin);
    }
}
