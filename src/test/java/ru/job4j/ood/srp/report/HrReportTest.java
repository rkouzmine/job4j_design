package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.comparator.SalaryDescComparator;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class HrReportTest {

    @Test
    public void whenSalarySortedDesc() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerFirst = new Employee("Alice", now, now, 500);
        Employee workerSecond = new Employee("Bob", now, now, 100);
        Employee workerThird = new Employee("Alex", now, now, 300);
        store.add(workerFirst);
        store.add(workerSecond);
        store.add(workerThird);

        Report result = new HrReport(store, new SalaryDescComparator());

        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerFirst.getName()).append(" ")
                .append(workerFirst.getSalary())
                .append(System.lineSeparator())
                .append(workerThird.getName()).append(" ")
                .append(workerThird.getSalary())
                .append(System.lineSeparator())
                .append(workerSecond.getName()).append(" ")
                .append(workerSecond.getSalary())
                .append(System.lineSeparator());

        assertThat(result.generate(employee -> true)).isEqualTo(expected.toString());
    }

}