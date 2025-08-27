package ru.job4j.ood.foodstorage.store;

import ru.job4j.ood.foodstorage.util.ExpirationCalculator;
import ru.job4j.ood.foodstorage.model.Food;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return ExpirationCalculator.getExpirationPercentage(food) >= 100;
    }
}
