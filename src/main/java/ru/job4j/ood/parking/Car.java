package ru.job4j.ood.parking;

public abstract class Car {
    private final String name;
    private final String type;

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
