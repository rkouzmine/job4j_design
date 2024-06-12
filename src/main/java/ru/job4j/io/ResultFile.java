package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/dataresult.txt")) {
            outputStream.write("Hello, world!".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}