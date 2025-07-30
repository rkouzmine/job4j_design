package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class JsonReportEngineTest {

    @Test
    void whenAccountantIsAlone() {
        MemoryStore store = new MemoryStore();
        Calendar hired = new GregorianCalendar(2016, Calendar.AUGUST, 26, 8, 0);
        Calendar fired = new GregorianCalendar(2026, Calendar.AUGUST, 26, 8, 0);
        Employee employee = new Employee("Alice K", hired, fired, 8000.0);
        store.add(employee);
        Report report = new JsonReportEngine(store);
        String expected = """
                [
                  {
                    "name": "Alice K",
                    "hired": "26:08:2016 08:00",
                    "fired": "26:08:2026 08:00",
                    "salary": 8000.0
                  }
                ]""";
        assertThat(report.generate(x -> true)).isEqualTo(expected);
    }

    @Test
    void whenAccountantsGenerated() {
        MemoryStore store = new MemoryStore();
        Employee employee1 = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        Employee employee2 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
        store.add(employee1);
        store.add(employee2);
        Report engine = new JsonReportEngine(store);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }

    @Test
    void whenFiltersAccountantsHiredAfter2007() {
        MemoryStore store = new MemoryStore();
        Employee employee1 = new Employee("Tony S",
                new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0),
                new GregorianCalendar(2025, Calendar.SEPTEMBER, 3, 0, 0),
                10500.0);
        Employee employee2 = new Employee("John W",
                new GregorianCalendar(2005, Calendar.DECEMBER, 20, 8, 15),
                new GregorianCalendar(2025, Calendar.JUNE, 8, 17, 30),
                10000.0);
        Employee employee3 = new Employee("Alice K",
                new GregorianCalendar(2016, Calendar.AUGUST, 26, 8, 0),
                new GregorianCalendar(2026, Calendar.AUGUST, 26, 8, 0),
                8000.0);
        store.add(employee1);
        store.add(employee2);
        store.add(employee3);
        Report report = new JsonReportEngine(store);
        String ex = """
                [
                  {
                    "name": "Alice K",
                    "hired": "26:08:2016 08:00",
                    "fired": "26:08:2026 08:00",
                    "salary": 8000.0
                  }
                ]""";
        assertThat(report.generate(em -> em.getHired().get(Calendar.YEAR) > 2007)).isEqualTo(ex);
    }

    @Test
    void whenNoAccountants() {
        MemoryStore store = new MemoryStore();
        Report report = new JsonReportEngine(store);
        String json = report.generate(x -> true);
        assertThat(json).isEqualTo("[]");
    }

}
