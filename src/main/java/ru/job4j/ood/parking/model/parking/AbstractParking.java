package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.*;

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
            }
        }
        return result;
    }

    @Override
    public List<Car> findAllCar() {
        List<Car> result =  new ArrayList<>();
        for (ParkingPlace place : places) {
            if (place.getCar() != null) {
                result.add(place.getCar());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "AbstractParking{"
                + "places=" + places
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractParking that = (AbstractParking) o;
        return Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (places != null ? places.hashCode() : 0);
        return result;
    }
}
