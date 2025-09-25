package ru.job4j.ood.dip.delivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ood.dip.delivery.courier.Courier;
import ru.job4j.ood.dip.delivery.model.Order;

import java.util.List;

public class CourierService {
    private static final Logger LOG = LoggerFactory.getLogger(CourierService.class.getName());

    private final List<Courier> couriers;

    public CourierService(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public void distribute(Order order) {
        boolean delivered = false;
        for (Courier c : couriers) {
            if (c.accept(order)) {
                c.deliver(order);
                delivered = true;
                break;
            }
        }
        if (!delivered) {
            LOG.warn("Нет подходящего курьера по заказу №{}", order.getId());
        }
    }
}
