package ru.job4j.ood.srp.ex.product;

public class ProductPrinter implements Printer {

    private final Repository repository;

    public ProductPrinter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void printAll() {
        for (Product product : repository.findAll()) {
            System.out.printf("%s - %d р.%n", product.getName(), product.getPrice());
        }
    }

}
