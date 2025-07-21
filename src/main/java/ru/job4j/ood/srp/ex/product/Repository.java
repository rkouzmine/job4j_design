package ru.job4j.ood.srp.ex.product;


import java.util.List;

public interface Repository {

    void add(Product product);

    Product findByName(String name);

    void remove(String name);

    List<Product> findAll();

}
