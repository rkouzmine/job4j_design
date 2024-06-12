package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}