package ru.job4j.ood.parking.model.parking;

public class ParkingPassengerCar extends AbstractParking {
    public ParkingPassengerCar(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("The number of parking spaces for passenger cars cannot be negative: " + size);
        }
        for (int i = 1; i <= size; i++) {
            getPlaces().add(new ParkingPlace(i));
        }
    }
}
