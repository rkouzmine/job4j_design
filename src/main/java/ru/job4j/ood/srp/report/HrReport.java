package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.comparator.SalaryDescComparator;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class HrReport implements Report {

    private final Store store;
    private final SalaryDescComparator salaryDescComparator;

    public HrReport(Store store, SalaryDescComparator salaryDescComparator) {
        this.store = store;
        this.salaryDescComparator = salaryDescComparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());

        List<Employee> employees = store.findBy(filter);
        employees.sort(salaryDescComparator);

        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
