package ru.job4j.ood.parking.model.car;

public abstract class Car {
    private final String number;
    private int parkingSpaceSize;

    public Car(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getParkingSpaceSize() {
        return parkingSpaceSize;
    }

    @Override
    public String toString() {
        return "Car{"
                + "number='" + number + '\''
                + ", parkingSpaceSize=" + parkingSpaceSize
                + '}';
    }
}
