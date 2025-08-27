package ru.job4j.ood.foodstorage.store;

import java.util.List;

public interface Store<T> {

    boolean accept(T t);

    void add(T t);

    void remove(T t);

    List<T> findByName(String name);

    void rename(T t, String newName);

    List<T> getAll();

}
