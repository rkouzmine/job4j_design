package ru.job4j.ood.parking.model.parking;

public class ParkingTruckCar extends AbstractParking {
    public ParkingTruckCar(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("The number of parking spaces for truck cars cannot be negative: " + size);
        }
        for (int i = 1; i <= size; i++) {
            getPlaces().add(new ParkingPlace(i));
        }
    }
}
