package ru.job4j.ood.dip.delivery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private static final Logger LOG = LoggerFactory.getLogger(Product.class.getName());

    private String name;
    private double price;
    private double weight;

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        LOG.debug("Создан продукт: {}", this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        LOG.debug("Имя продукта обновлено: {}", name);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        LOG.debug("Цена продукта обновлена: {}", price);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        LOG.debug("Вес продукта обновлен: {}", weight);
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", weight=" + weight
                + '}';
    }
}
