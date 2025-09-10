package ru.job4j.ood.isp.car;

public class Tesla implements Car {
    @Override
    public void openTrunk() {
        System.out.println("С багажником");
    }

    @Override
    public void refuel() {
        throw new UnsupportedOperationException("Электромобиль не заправляется бензином");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Заряжается от сети");
    }
}
