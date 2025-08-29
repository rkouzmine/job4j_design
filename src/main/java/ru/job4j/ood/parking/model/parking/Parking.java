package ru.job4j.ood.parking.model.parking;

public interface Parking<T> {

    void add(T t);

    void remove(T t);

}
