package ru.job4j.ood.dip.delivery.courier;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ood.dip.delivery.model.Order;
import ru.job4j.ood.dip.delivery.service.OrderCalculator;

public class BikeCourier implements Courier {
    private static final Logger LOG = LoggerFactory.getLogger(BikeCourier.class.getName());

    private final OrderCalculator calculator;
    private static final double MAX_WEIGHT = 5;

    public BikeCourier(OrderCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public boolean accept(Order order) {
        boolean result = calculator.calculateTotalWeight(order) <= MAX_WEIGHT;
        LOG.debug("BikeCourier {} заказ №{}: canAccept={}", (result ? "принимает" : "отклоняет"), order.getId(), result);
        return result;
    }

    @Override
    public void deliver(@NotNull Order order) {
        LOG.info("Заказ №{} доставит велокурьер", order.getId());
    }
}
