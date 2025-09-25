package ru.job4j.ood.dip.delivery.courier;

import ru.job4j.ood.dip.delivery.model.Order;

public interface Courier {

    boolean accept(Order order);

    void deliver(Order order);

}
