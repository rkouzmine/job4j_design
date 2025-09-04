package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.*;

public class ParkingCar implements Parking<Car> {
    private final int sizeFirst;
    private final int sizeSecond;

    private final List<ParkingPlace> passengerPlaces = new ArrayList<>();
    private final List<ParkingPlace> truckPlaces = new ArrayList<>();

    public ParkingCar(int sizeFirst, int sizeSecond) {
        if (sizeFirst < 0) {
            throw new IllegalArgumentException("The number of parking spaces for passenger cars cannot be negative: " + sizeFirst);
        }
        if (sizeSecond < 0) {
            throw new IllegalArgumentException("The number of parking spaces for truck cars cannot be negative: " + sizeSecond);
        }
        this.sizeFirst = sizeFirst;
        for (int i = 1; i <= sizeFirst; i++) {
            passengerPlaces.add(new ParkingPlace(i));
        }
        this.sizeSecond = sizeSecond;
        for (int i = 1; i <= sizeSecond; i++) {
            truckPlaces.add(new ParkingPlace(i));
        }
    }

    public int getSizeFirst() {
        return sizeFirst;
    }


    public int getSizeSecond() {
        return sizeSecond;
    }

    public List<ParkingPlace> getPassengerPlaces() {
        return passengerPlaces;
    }

    public List<ParkingPlace> getTruckPlaces() {
        return truckPlaces;
    }

    @Override
    public String toString() {
        return "ParkingCar{"
                + "sizeFirst=" + sizeFirst
                + ", sizeSecond=" + sizeSecond
                + ", passengerPlaces=" + passengerPlaces
                + ", truckPlaces=" + truckPlaces
                + '}';
    }

    @Override
    public boolean add(Car car) {
        int size = car.getParkingSpaceSize();
        boolean parked = false;
        if (size == 1) {
            parked = parkInFirstAvailable(passengerPlaces, car);
        } else if (size > 1) {
            parked = parkInFirstAvailable(truckPlaces, car);
            if (!parked) {
                parked = parkInFirstAvailableSequence(passengerPlaces, car, size);
            }
        }
        return parked;
    }

    private boolean parkInFirstAvailable(List<ParkingPlace> places, Car car) {
        boolean result = false;
        for (ParkingPlace place : places) {
            if (place.getCar() == null) {
                place.park(car);
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean parkInFirstAvailableSequence(List<ParkingPlace> places, Car car, int size) {
        boolean result = false;
        for (int i = 0; i <= places.size() - size && !result; i++) {
            boolean canFit = true;
            for (int j = 0; j < size; j++) {
                if (places.get(i + j).getCar() != null) {
                    canFit = false;
                    break;
                }
            }
            if (canFit) {
                for (int j = 0; j < size; j++) {
                    places.get(i + j).park(car);
                }
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Car car) {
        boolean result = false;
        if (car.getParkingSpaceSize() == 1) {
            for (ParkingPlace place : passengerPlaces) {
                if (place.getCar() != null && place.getCar().equals(car)) {
                    place.release(car);
                    result = true;
                }
            }
        } else if (car.getParkingSpaceSize() > 1) {
            for (ParkingPlace place : truckPlaces) {
                if (place.getCar() != null && place.getCar().equals(car)) {
                    place.release(car);
                    result = true;
                }
            }
        }
        return result;
    }

    public List<Car> getParkingSpaces(List<ParkingPlace> places) {
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
        List<Car> result = new ArrayList<>();
        result.addAll(new LinkedHashSet<>(getParkingSpaces(passengerPlaces)));
        result.addAll(new LinkedHashSet<>(getParkingSpaces(truckPlaces)));
        return result;
    }
}
