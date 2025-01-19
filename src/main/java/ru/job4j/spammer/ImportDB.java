package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(line -> {
                String[] data = line.split(";");
                if (data.length != 2) {
                    throw new IllegalArgumentException("Invalid data format: " + line);
                }
                String first = data[0].trim();
                String second = data[1].trim();

                if (first.isEmpty() || second.isEmpty()) {
                    throw new IllegalArgumentException("Empty field in line: " + line);
                }
                users.add(new User(first, second));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection()) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    public void createTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "id SERIAL PRIMARY KEY, "
                            + "name TEXT NOT NULL, "
                            + "email TEXT NOT NULL UNIQUE"
                            + ");"
            )) {
                preparedStatement.execute();
            }
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        return DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        );
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.createTable();
        dataBase.save(dataBase.load());
    }
}