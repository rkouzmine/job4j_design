package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

public class ParkingPlace {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void park(Car car) {
        this.car = car;
    }

    public void release(Car car) {
    }

    public String toString() {
        return car != null ? car.toString() : "[empty]";
    }
}
