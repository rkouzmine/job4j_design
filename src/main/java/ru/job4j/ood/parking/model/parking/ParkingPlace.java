package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

public class ParkingPlace {

    private final int placeId;

    private Car car;

    public ParkingPlace(int placeId) {
        this.placeId = placeId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public Car getCar() {
        return car;
    }

    public void park(Car car) {
        this.car = car;
    }

    public void release(Car car) {
        this.car = null;
    }

    public boolean isFree() {
        return car == null;
    }

    @Override
    public String toString() {
        String result;
        if (car != null) {
            result = "ParkingPlace{"
                    + "placeId=" + placeId
                    + ", car=" + car
                    + '}';

        } else {
            result = "[empty]";
        }
        return result;

    }

}
