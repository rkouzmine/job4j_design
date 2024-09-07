package ru.job4j.io.serialization.json;

public class Engine {
    private final int horsePower;

    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "horsePower=" + horsePower
                + '}';
    }
}
