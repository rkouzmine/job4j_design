package ru.job4j.ood.isp.device.first;

public class Server implements Device {

    @Override
    public void in(String data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void calculate() {
        System.out.println("Do some work!");
    }

    @Override
    public void output() {
        throw new UnsupportedOperationException();
    }
}