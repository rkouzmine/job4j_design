package ru.job4j.ood.foodstorage.util;

import ru.job4j.ood.foodstorage.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExpirationCalculator {
    public static int getExpirationPercentage(Food food) {
        int result = 100;
        long totalDays = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long passedDays = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        if (totalDays > 0) {
            result = (int) (passedDays * 100 / totalDays);
        }
        return result;
    }
}
