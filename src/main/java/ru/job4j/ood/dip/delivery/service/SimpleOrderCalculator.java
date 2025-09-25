package ru.job4j.ood.dip.delivery.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ood.dip.delivery.model.Order;
import ru.job4j.ood.dip.delivery.model.Product;

public class SimpleOrderCalculator implements OrderCalculator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleOrderCalculator.class);

    @Override
    public double calculateTotalWeight(@NotNull Order order) {
        double result = 0;
        for (Product product : order.getProducts()) {
            result += product.getWeight();
        }
        LOG.debug("Общий вес заказа №{}: {}", order.getId(), result);
        return result;
    }
}
