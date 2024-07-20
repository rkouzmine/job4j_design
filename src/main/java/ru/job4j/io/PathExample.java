package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("path/paths");
        Files.createDirectories(directory);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);

        /*
        Методы toFile() и toPath() для перевода в объекты File и Path
         */
        File file = path.toFile();
        System.out.println(file);
        Path pathAgain = file.toPath();
        System.out.println(pathAgain);

        /*
        Методы Path, Files.
         */
        System.out.println();
        System.out.println("Файл/директория существует?: " + Files.exists(path));
        System.out.println("Это директория?: " + Files.isDirectory(path));
        System.out.println("Это файл?: " + Files.isRegularFile(path));
        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path.isAbsolute());
        System.out.println("Родительская директория файла: " + path.getParent());
        System.out.println("Абсолютный путь к файлу: " + path.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + directory.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path));
        System.out.println("Доступен для записи?: " + Files.isWritable(path));
        System.out.println();

        /*
        Перемещение файла и его удаление
         */
        Path pathForDelete = Files.move(path, Path.of("path/path_move.txt"));
        Files.delete(pathForDelete);

        /*
        Методы получения информации о файлах внутри директории.
         */
        Path target = Paths.get("path");
        Path path1 = Path.of("path/paths/path1");
        Files.createFile(path1);
        Path path2 = Path.of("path/path2");
        Files.createFile(path2);
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }
}