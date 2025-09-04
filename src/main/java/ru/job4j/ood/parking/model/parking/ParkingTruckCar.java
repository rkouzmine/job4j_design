package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class ParkingTruckCar implements Parking<Car> {
    private final List<ParkingPlace> places = new ArrayList<>();

    public ParkingTruckCar(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("The number of parking spaces for truck cars cannot be negative: " + size);
        }
        for (int i = 1; i <= size; i++) {
            places.add(new ParkingPlace(i));
        }
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        for (ParkingPlace truckPlace : places) {
            if (truckPlace.getCar() == null) {
                truckPlace.park(car);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Car car) {
        boolean result = false;
        for (ParkingPlace place : places) {
            if (place.getCar() != null && place.getCar().equals(car)) {
                place.release(car);
                result = true;
            }
        }
        return result;
    }

    public List<Car> getTruckParkingSpaces(List<ParkingPlace> places) {
        List<Car> result = new ArrayList<>();
        for (ParkingPlace place : places) {
            if (place.getCar() != null) {
                result.add(place.getCar());
            }
        }
        return result;
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(new LinkedHashSet<>(getTruckParkingSpaces(places)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingTruckCar that = (ParkingTruckCar) o;
        return Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(places);
    }
}
