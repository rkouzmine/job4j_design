package ru.job4j.ood.srp.ex.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository {

    private final List<Product> repository = new ArrayList<>();

    @Override
    public void add(Product product) {
        repository.add(product);
    }

    @Override
    public Product findByName(String name) {
        Product result = null;
        for (Product store : repository) {
            if (name.equals(store.getName())) {
                result = store;
                break;
            }
        }
        return result;
    }

    @Override
    public void remove(String name) {
        if (name != null) {
            repository.removeIf(product -> name.equals(product.getName()));
        }
    }

    @Override
    public List<Product> findAll() {
        return repository;
    }

}
