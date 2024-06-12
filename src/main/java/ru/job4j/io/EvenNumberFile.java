package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                text.append((char) read);
            }

            String[] numbers = text.toString().split(System.lineSeparator());
            for (String num : numbers) {
                int number = Integer.parseInt(num);
                if (number % 2 == 0) {
                    System.out.println(number);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}