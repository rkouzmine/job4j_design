package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.List;

public class ParkingCar implements Parking<Car> {
    private final int sizeFirst;
    private final int sizeSecond;

    private List<ParkingPlace> passengerPlaces = new ArrayList<>();
    private List<ParkingPlace> truckPlaces = new ArrayList<>();

    public ParkingCar(int sizeFirst, int sizeSecond) {
        this.sizeFirst = sizeFirst;
        this.sizeSecond = sizeSecond;
    }

    public int getSizeFirst() {
        return sizeFirst;
    }


    public int getSizeSecond() {
        return sizeSecond;
    }


    @Override
    public void add(Car car) {

    }

    @Override
    public void remove(Car car) {

    }
}
