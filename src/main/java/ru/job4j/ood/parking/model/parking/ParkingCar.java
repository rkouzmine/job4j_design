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
        for (int i = 1; i <= sizeFirst; i++) {
            passengerPlaces.add(new ParkingPlace());
        }
        this.sizeSecond = sizeSecond;
        for (int i = 1; i <= sizeSecond; i++) {
            truckPlaces.add(new ParkingPlace());
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
    public void add(Car car) {
       int size = car.getParkingSpaceSize();
       if (size == 1) {
           for (ParkingPlace place : passengerPlaces) {
               if (place.getCar() == null) {
                   place.park(car);
               }
           }
       } else if (size > 1) {
           for (ParkingPlace place : truckPlaces) {
               if (place.getCar() == null) {
                   place.park(car);
               }
           }
       }

    }

    @Override
    public void remove(Car car) {

    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    public List<Car> getCarsFromParkingSpaces(List<ParkingPlace> passengerPlaces) {
        List<Car> result = new ArrayList<>();
        for (ParkingPlace place : passengerPlaces) {
            result.add(place.getCar());
        }
        return result;
    }
}
