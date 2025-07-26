package ru.job4j.ood.ocp.person;

public class Employee extends Person {
    private String department;

    public Employee(String name, int age, String department) {
        super(name, age);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
