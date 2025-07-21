package ru.job4j.ood.srp.ex.employee;

public class SalaryCalculator implements Calculator<Employee> {
    @Override
    public double calculateSalary(Employee employee, double multiplier, double bonus) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Multiplier должен быть больше 0");
        }
        return employee.getSalary() * multiplier + bonus;
    }
}
