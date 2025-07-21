package ru.job4j.ood.srp.ex.user;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
