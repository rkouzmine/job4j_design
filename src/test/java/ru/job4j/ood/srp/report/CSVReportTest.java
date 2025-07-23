package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class CSVReportTest {

    @Test
    public void whenSingleEmployeeCSVReport() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Alice", now, now, 5000);
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        Report result = new CSVReport(store, parser);

        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(String.format("%.2f", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(result.generate(employee -> true)).isEqualTo(expected.toString());
    }

}