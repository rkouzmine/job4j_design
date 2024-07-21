package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Set<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        map.computeIfAbsent(fileProperty, k -> new HashSet<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        for (Map.Entry<FileProperty, Set<Path>> entry : map.entrySet()) {
            Set<Path> duplicates = entry.getValue();
            if (duplicates.size() > 1) {
                System.out.printf("Duplicates:%n%s - %s bytes%n",
                        entry.getKey().getName(),
                        entry.getKey().getSize());
                for (Path duplicate : duplicates) {
                    System.out.println("  " + duplicate.toAbsolutePath());
                }
                System.out.println();
            }
        }
        map.clear();
        return FileVisitResult.CONTINUE;
    }
}