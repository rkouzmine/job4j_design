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

        String expected = String.format(
                "Name; Salary;%nAlice 500,00%nAlex 300,00%nBob 100,00%n"
        );
        assertThat(result.generate(employee -> true)).isEqualTo(expected);
    }

}