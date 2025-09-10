package ru.job4j.ood.isp.weapon;

public class Knife implements Weapon {
    @Override
    public void canShoot() {
        throw new UnsupportedOperationException("Нож не стреляет");
    }

    @Override
    public void canReload() {
        throw new UnsupportedOperationException("Нож не перезаряжается");
    }
}