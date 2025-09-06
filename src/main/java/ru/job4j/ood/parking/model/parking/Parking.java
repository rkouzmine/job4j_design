package ru.job4j.ood.parking.model.parking;

import java.util.List;

public interface Parking<T> {

    boolean add(T t);

    boolean remove(T t);

    List<T> findAllCar();

}
