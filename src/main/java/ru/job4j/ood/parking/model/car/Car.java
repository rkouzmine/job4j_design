package ru.job4j.ood.parking.model.car;

import java.util.Objects;

public abstract class Car {
    private final String number;
    private int parkingSpaceSize;

    public Car(String number, int parkingSpaceSize) {
        this.number = number;
        this.parkingSpaceSize = parkingSpaceSize;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return parkingSpaceSize == car.parkingSpaceSize && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, parkingSpaceSize);
    }
}
