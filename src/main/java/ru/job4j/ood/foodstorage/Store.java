package ru.job4j.ood.foodstorage;

import java.util.List;

public interface Store<T> {
    void add(T t);
    void remove(T t);
    List<T> findByName(String name);
    List<T> findAll();
}
