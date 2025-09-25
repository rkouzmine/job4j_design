package ru.job4j.ood.dip.examples.payment;

public class OrderService {

    private final Payment payment;

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void payOrder(Order order) {

        payment.pay(order.getAmount());
    }
}