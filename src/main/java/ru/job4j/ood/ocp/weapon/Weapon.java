package ru.job4j.ood.ocp.weapon;

public abstract class Weapon {
    private final String name;

    public Weapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
