package ru.job4j.ood.parking.model.parking;

import java.util.List;

public interface Parking<T> {

    void add(T t);

    void remove(T t);

    List<T> getAll();

}
