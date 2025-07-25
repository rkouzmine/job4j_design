package ru.job4j.ood.ocp;

import java.util.List;

public class Weapon {
    private final String type;

    public Weapon(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        List<Weapon> weaponList = List.of(new Weapon("Огнестрельное"), new Weapon("Холодное"));
        weaponList.forEach(x -> System.out.println(x.type));
    }
}
