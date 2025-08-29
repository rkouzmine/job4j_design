package ru.job4j.ood.parking.model.car;

public class TruckCar extends Car {
    private final int parkingSpaceSize;

    public TruckCar(String number, int parkingSpaceSize) {
        super(number);
        this.parkingSpaceSize = parkingSpaceSize;
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    public int getParkingSpaceSize() {
        return parkingSpaceSize;
    }
}
