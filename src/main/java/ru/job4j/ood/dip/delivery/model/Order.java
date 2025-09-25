package ru.job4j.ood.dip.delivery.model;


import java.util.List;

public interface Order {

    int getId();

    boolean add(Product product);

    List<Product> getProducts();


}
