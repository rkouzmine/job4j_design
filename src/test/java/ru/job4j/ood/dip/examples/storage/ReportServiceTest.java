package ru.job4j.ood.dip.examples.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class ReportServiceTest {

    @Test
    void whenCreateReportThenReportIsSaved() throws IOException {
        Storage fileStorage = new FileStorage();
        ReportService reportService = new ReportService(fileStorage);

        String str = """
                Ночь, улица, фонарь, аптека,
                Бессмысленный и тусклый свет.
                Живи еще хоть четверть века —
                Всё будет так. Исхода нет.
                Умрешь — начнешь опять сначала,
                И повторится всё, как встарь:
                Ночь, ледяная рябь канала,
                Аптека, улица, фонарь.
                """;

        reportService.createReport(str);

        Path path = Path.of("data/odd/dip/storage/file.txt");

        String file = Files.readString(path);

        assertThat(file).contains(str);
    }

}