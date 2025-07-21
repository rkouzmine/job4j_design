package ru.job4j.ood.srp.ex.employee;

public class EmployeePrinter implements Printer<Employee> {
    private final Repository<Employee> repository;

    public EmployeePrinter(Repository<Employee> repository) {
        this.repository = repository;
    }

    @Override
    public void printAllEmployee() {
        for (Employee employee : repository.findAll()) {
            System.out.printf("Имя: %s, отдел: %s, зарплата: %d%n",
                    employee.getName(), employee.getDepartment(), employee.getSalary());
        }
    }

}
