package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path path = Path.of(cachingDir, key);
        validatePath(path);
        String result;
        try {
            result = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void validatePath(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    String.format("Файл '%s' не найден в директории '%s'.",
                            path.getFileName(),
                            path.getParent().toAbsolutePath()
                    ));
        }

        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Путь '%s' указывает на директорию, а не на файл. Укажите имя файла.",
                            path.toAbsolutePath()
                    ));
        }

        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Недостаточно прав для чтения файла '%s'. Проверьте права доступа к файлу.",
                            path.getFileName()
                    ));
        }
    }

}