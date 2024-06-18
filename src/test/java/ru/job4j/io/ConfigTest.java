package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenReadingFileWithCommentsAndEmptyLines() {
        String path = "./data/pair_strings_and_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Test")).isNull();
    }

}