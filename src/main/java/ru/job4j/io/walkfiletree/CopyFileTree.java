package ru.job4j.io.walkfiletree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileTree {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("data/test");
        Path target = Paths.get("data/copyFiles");
        Files.walkFileTree(source, new FileVisitorCopy(source, target));
    }
}

class FileVisitorCopy extends SimpleFileVisitor<Path> {
    Path source;
    Path target;

    public FileVisitorCopy(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newDir = target.resolve(source.relativize(dir));
        Files.copy(dir, newDir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path newFile = target.resolve(source.relativize(file));
        Files.copy(file, newFile);
        return FileVisitResult.CONTINUE;
    }
}
