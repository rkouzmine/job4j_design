package ru.job4j.ood.srp.user;

public class Main {
    public static void main(String[] args) {
        User userFirst = new User("Alice", "alice@ya.ru");
        User userSecond = new User("Alex", "alex@gmail.com");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(userFirst);
        userRepository.addUser(userSecond);
        System.out.println(userRepository.getAllUsers());
    }
}
