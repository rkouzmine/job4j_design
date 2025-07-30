package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.formatter.DateTimeParser;

import java.util.Calendar;

public class EmployeeJson {
    private String name;
    private String hired;
    private String fired;
    private double salary;

    public EmployeeJson(Employee employee, DateTimeParser<Calendar> dateTimeParser) {
        this.name = employee.getName();
        this.hired = dateTimeParser.parse(employee.getHired());
        this.fired = dateTimeParser.parse(employee.getFired());
        this.salary = employee.getSalary();
    }
}
