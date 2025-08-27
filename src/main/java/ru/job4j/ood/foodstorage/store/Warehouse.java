package ru.job4j.ood.foodstorage.store;

import ru.job4j.ood.foodstorage.util.ExpirationCalculator;
import ru.job4j.ood.foodstorage.model.Food;

public class Warehouse extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        int percent = ExpirationCalculator.getExpirationPercentage(food);
        return percent < 25;
    }
}
