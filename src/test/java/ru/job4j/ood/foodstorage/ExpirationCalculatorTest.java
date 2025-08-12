package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.util.ExpirationCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ExpirationCalculatorTest {

    @Test
    void whenProductIsThreeQuartersThroughShelfLifeThanReturn75Percent() {
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(3), 100.0, 20);
        int result = ExpirationCalculator.getExpirationPercentage(food);
        assertThat(result).isEqualTo(75);
    }

    @Test
    void whenProductIsExpiredThanReturn100Percent() {
        LocalDate now = LocalDate.now();
        Food food = new Food("food", now, now.minusDays(7), 100.0, 20);
        int result = ExpirationCalculator.getExpirationPercentage(food);
        assertThat(result).isEqualTo(100);
    }

}