package ru.job4j.ood.srp.product;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductPrinter productPrinter = new ProductPrinter(productRepository);
        ProductStatistics productStatistics = new ProductStatistics(productRepository);

        productRepository.add(new Product("Хлеб", 100));
        productRepository.add(new Product("Молоко", 150));
        productRepository.add(new Product("Каша", 200));

        System.out.println(productRepository.findByName("Хлеб"));
        productPrinter.printAll();
        productRepository.remove("Каша");
        productPrinter.printAll();

        System.out.println(productStatistics.count());
        System.out.println(productStatistics.totalPrice());
    }
}
