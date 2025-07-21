package ru.job4j.ood.srp.ex.user;

import java.util.List;

public interface Repository<T> {

    void addUser(T t);

    List<T> getAllUsers();

}
