package ru.job4j.ood.isp.weapon;

public class Bow implements Shootable {
    @Override
    public void canShoot() {
        System.out.println("Лук стреляет");
    }
}
