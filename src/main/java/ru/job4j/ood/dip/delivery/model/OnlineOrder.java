package ru.job4j.ood.dip.delivery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OnlineOrder implements Order {
    private static final Logger LOG = LoggerFactory.getLogger(OnlineOrder.class.getName());
    private static int counter = 1;
    private int id = 1;

    private final List<Product> productList = new ArrayList<>();

    public OnlineOrder() {
        this.id = counter++;
        LOG.debug("Создан заказ №{}", id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean add(Product product) {
        boolean result = productList.add(product);
        LOG.debug("Продукт {} добавлен в заказ №{}", product.getName(), id);
        return result;
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }

}
