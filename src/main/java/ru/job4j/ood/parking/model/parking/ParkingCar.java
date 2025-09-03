package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    public String toString() {
        return "ParkingCar{"
                + "sizeFirst=" + sizeFirst
                + ", sizeSecond=" + sizeSecond
                + ", passengerPlaces=" + passengerPlaces
                + ", truckPlaces=" + truckPlaces
                + '}';
    }

    @Override
    public void add(Car car) {
        int sizeCar = car.getParkingSpaceSize();

        if (sizeCar == 1) {
            for (ParkingPlace place : passengerPlaces) {
                if (place.getCar() == null) {
                    place.park(car);
                    return;
                }
            }
        } else if (sizeCar > 1) {
            for (ParkingPlace place : truckPlaces) {
                if (place.getCar() == null) {
                    place.park(car);
                    return;
                }
            }
            int freeCount = 0;
            int startIndex = -1;

            for (int i = 0; i < passengerPlaces.size(); i++) {
                if (passengerPlaces.get(i).getCar() == null) {
                    if (freeCount == 0) {
                        startIndex = i;
                    }
                    freeCount++;

                    if (freeCount == sizeCar) {
                        for (int j = 0; j < sizeCar; j++) {
                            passengerPlaces.get(startIndex + j).park(car);
                        }
                        return;

                    }
                } else {
                    freeCount = 0;
                    startIndex = -1;
                }
            }
        }

    }

    @Override
    public void remove(Car car) {

    }

    @Override
    public List<Car> getAll() {
        List<Car> result = new ArrayList<>();
        result.addAll(getParkedCars(passengerPlaces));
        result.addAll(getParkedCars(truckPlaces));
        return result;
    }

    public List<Car> getCarsFromParkingSpaces(List<ParkingPlace> passengerPlaces) {
        List<Car> result = new ArrayList<>();
        for (ParkingPlace place : passengerPlaces) {
            if (place.getCar() != null) {
                result.add(place.getCar());
            }
        }
        return result;
    }

    public List<Car> getParkedCars(List<ParkingPlace> places) {
        Set<Car> result = new LinkedHashSet<>();
        for (ParkingPlace place : places) {
            if (place.getCar() != null) {
                result.add(place.getCar());
            }
        }
        return new ArrayList<>(result);
    }
}
