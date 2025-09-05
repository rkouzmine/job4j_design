package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking implements Parking<Car> {
    private final List<ParkingPlace> places = new ArrayList<>();

    @Override
    public boolean add(Car car) {
        boolean result = false;
        for (ParkingPlace place : getPlaces()) {
            if (place.getCar() == null) {
                place.park(car);
                result = true;
                break;
            }
        }
        return result;
    }

    public List<ParkingPlace> getPlaces() {
        return places;
    }

    @Override
    public boolean remove(Car car) {
        boolean result = false;
        for (ParkingPlace place : places) {
            if (car.equals(place.getCar())) {
                place.release(car);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> result = new ArrayList<>();
        for (ParkingPlace place : places) {
            if (!place.isFree()) {
                result.add(place.getCar());
            }
        }
        return result;
    }
}
