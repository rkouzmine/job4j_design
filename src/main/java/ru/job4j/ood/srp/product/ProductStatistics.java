package ru.job4j.ood.srp.product;

public class ProductStatistics implements Statistics {

    private final Repository repository;

    public ProductStatistics(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int count() {
        return repository.findAll().size();
    }

    @Override
    public long totalPrice() {
        long result = 0;
        for (Product product : repository.findAll()) {
            result += product.getPrice();
        }
        return result;
    }

}
