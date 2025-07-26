package ru.job4j.ood.ocp.person;

public class EmployeePrinter implements Printer<Employee> {
    @Override
    public void printEmployee(Employee e) {
        System.out.printf("Name: %s, age: %d, department: %s%n", e.getName(), e.getAge(), e.getDepartment());
    }
}
