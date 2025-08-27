package ru.job4j.ood.foodstorage.store;

import ru.job4j.ood.foodstorage.util.ExpirationCalculator;
import ru.job4j.ood.foodstorage.model.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        int percent = ExpirationCalculator.getExpirationPercentage(food);
        boolean result = percent >= 25 && percent < 100;
        if (result && percent > 75) {
            double discount = food.getPrice() / 100 * food.getDiscount();
            food.setPrice(food.getPrice() - discount);
        }
        return result;
    }
}
