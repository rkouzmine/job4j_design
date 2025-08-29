package ru.job4j.ood.parking.model.car;

public class PassengerCar extends Car {
    private final int parkingSpaceSize = 1;
    public PassengerCar(String number) {
        super(number);
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    public int getParkingSpaceSize() {
        return parkingSpaceSize;
    }
}
