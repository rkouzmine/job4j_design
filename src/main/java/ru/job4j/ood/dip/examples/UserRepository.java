package ru.job4j.ood.dip.examples;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<String> users = new ArrayList<>();

    public void addUser(String user) {
        users.add(user);
    }
}