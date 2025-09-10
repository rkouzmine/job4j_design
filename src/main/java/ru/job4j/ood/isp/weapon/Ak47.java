package ru.job4j.ood.isp.weapon;

public class Ak47 implements Shootable, Reloadable {
    @Override
    public void reload() {
        System.out.println("Перезаряжается");
    }

    @Override
    public void canShoot() {
        System.out.println("Стреляет");
    }
}
