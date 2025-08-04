package ru.job4j.ood.foodstorage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store<Food> {

    List<Food> foodlist = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodlist.add(food);
    }

    @Override
    public void remove(Food food) {
        foodlist.remove(food);
    }

    @Override
    public List<Food> findByName(String name) {
        List<Food> result = new ArrayList<>();
        for (Food food : foodlist) {
            if (name != null && name.equals(food.getName())) {
                result.add(food);
            }
        }
        return result;
    }

    @Override
    public List<Food> findAll() {
        return foodlist;
    }
}
