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
        String result = null;
        if (Files.exists(path)) {
            if (Files.isRegularFile(path)) {
                if (Files.isReadable(path)) {
                    try {
                        result = Files.readString(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(String.format(
                            "Недостаточно прав для чтения файла '%s'. Проверьте права доступа к файлу.",
                            key
                    ));
                }
            } else {
                System.out.println(String.format(
                        "Путь '%s' указывает на директорию, а не на файл. Укажите имя файла.",
                        path.toAbsolutePath()
                ));
            }
        } else {
            System.out.println(String.format(
                    "Файл '%s' не найден в директории '%s'.",
                    key,
                    cachingDir
            ));
        }
        return result;
    }
}