package ru.job4j.ood.isp.car;

public class Tesla implements Chargeable, HasTrunk {
    @Override
    public void openTrunk() {
        System.out.println("С багажником");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Заряжается от сети");
    }
}
