package ru.job4j.ood.srp.ex.employee;

public interface Calculator<T> {

    double calculateSalary(T t, double multiplier, double bonus);

}
