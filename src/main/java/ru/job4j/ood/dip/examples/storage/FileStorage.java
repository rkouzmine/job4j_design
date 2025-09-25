package ru.job4j.ood.dip.examples.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorage implements Storage {
    private static final Logger LOG = LoggerFactory.getLogger(FileStorage.class);

    private static final Path DIRECTORY = Path.of("data/odd/dip/storage");
    private static final Path FILE = DIRECTORY.resolve("file.txt");


    @Override
    public void save(String data) {
        try {
            if (Files.notExists(DIRECTORY)) {
                Files.createDirectories(DIRECTORY);
                LOG.info("Создана директория: {}", DIRECTORY.toAbsolutePath());
            }

            if (Files.notExists(FILE)) {
                Files.createFile(FILE);
                LOG.info("Создан новый файл: {}", FILE);
            }

            try (PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(FILE.toFile(), true), StandardCharsets.UTF_8)))) {
                out.println(data);
                LOG.debug("Записаны данные: {}", data);
            }

            LOG.info("Данные успешно сохранены в файл: {}", FILE);

        } catch (IOException e) {
            LOG.error("Ошибка при сохранении данных в файл", e);
        }
    }
}