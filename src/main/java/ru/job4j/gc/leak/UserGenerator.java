package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final int NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            StringBuilder name = new StringBuilder();
            name.append(surnames.get(random.nextInt(surnames.size()))).append(SEPARATOR)
                    .append(names.get(random.nextInt(names.size()))).append(SEPARATOR)
                    .append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(name.toString()));
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES).stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            surnames = read(PATH_SURNAMES).stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            patrons = read(PATH_PATRONS).stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}