package ru.job4j.ood.parking.model.car;

public abstract class Car {
    private final String number;

    public Car(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
