package ru.job4j.ood.srp.ex.employee;

import java.util.List;

public interface Repository<T> {

    void addEmployee(T t);

    void deleteEmployee(T t);

    List<T> findByName(String name);

    List<T> findAll();
}
