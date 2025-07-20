package ru.job4j.ood.srp.employee;

public interface Calculator<T> {

    double calculateSalary(T t, double multiplier, double bonus);

}
