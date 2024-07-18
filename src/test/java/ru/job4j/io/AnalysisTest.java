package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void serverLogTest(@TempDir Path temDir) throws IOException {
        File source = temDir.resolve("source.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    500 10:59:01
                    400 11:01:02
                    300 11:02:02""");
        }
        File target = temDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:57:01;11:02:02;").hasToString(result.toString());
    }

}