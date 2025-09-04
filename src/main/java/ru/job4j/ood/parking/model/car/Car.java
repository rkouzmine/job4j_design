package ru.job4j.ood.parking.model.car;

import java.util.Objects;

public abstract class Car {
    private final String number;
    private final int parkingSpaceSize;

    public Car(String number, int parkingSpaceSize) {
        this.number = validateNumber(number);
        this.parkingSpaceSize = validateParkingSize(parkingSpaceSize);
    }

    private String validateNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("The vehicle number cannot be empty");
        }
        return number;
    }

    private int validateParkingSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                    "The parking size of the vehicle cannot be negative: " + size
            );
        }
        return size;
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
        int result = 17;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + parkingSpaceSize;
        return result;
    }
}
