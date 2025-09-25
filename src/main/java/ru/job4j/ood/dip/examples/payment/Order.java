package ru.job4j.ood.dip.examples.payment;

public class Order {
    private final int id;
    private final double amount;

    public Order(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}