package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validationArgs(args.length);
        Path start = Path.of(args[0]);
        validationFile(start);
        search(start, path -> path.toFile()
                .getName()
                .endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validationArgs(int num) {
        if (num == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
    }

    private static void validationFile(Path file) {
        if (!file.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.toFile().getAbsoluteFile()));
        }
        if (!file.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.toFile().getAbsoluteFile()));
        }
    }
}