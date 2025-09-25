package ru.job4j.ood.dip.examples.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaypalPayment implements Payment {
    private static final Logger LOG = LoggerFactory.getLogger(PaypalPayment.class.getName());

    @Override
    public void pay(double amount) {
        LOG.info("Оплата через PayPal: {}", amount);
    }
}