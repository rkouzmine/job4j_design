package ru.job4j.ood.dip.delivery.service;

import ru.job4j.ood.dip.delivery.model.Order;

public interface OrderCalculator {

    double calculateTotalWeight(Order order);

}
