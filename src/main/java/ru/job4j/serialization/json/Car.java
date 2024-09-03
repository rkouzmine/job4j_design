package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean isElectric;
    private final String model;
    private final int year;
    private final Engine engine;
    private final String[] features;

    public Car(boolean isElectric, String model, int year, Engine engine, String[] features) {
        this.isElectric = isElectric;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectric=" + isElectric
                + ", model='" + model + '\''
                + ", year=" + year
                + ", engine=" + engine
                + ", features=" + Arrays.toString(features)
                + '}';
    }
}
