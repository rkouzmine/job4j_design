package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CrossPlatformFileReader {
    public static void main(String[] args) {
        /* Создание кроссплатформенного пути для файла "data/test/log.txt" */
        Path path = FileSystems.getDefault().getPath("data", "test", "log.txt");
        /* Чтение файла с использованием BufferedReader */
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}