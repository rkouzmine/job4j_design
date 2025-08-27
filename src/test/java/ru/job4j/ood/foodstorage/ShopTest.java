package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.store.Shop;
import ru.job4j.ood.foodstorage.util.ExpirationCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    void whenShelfLifeOfProductIsMore75ThanPercentDiscountIsApplied() {
        Shop shop = new Shop();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(4), 100.0, 20);
        boolean accepted = shop.accept(food);
        assertThat(accepted).isTrue();
        assertThat(food.getPrice()).isEqualTo(80.0);
    }

    @Test
    void whenShelfLifeOfProductIs75PercentThanDiscountDoesNotApply() {
        Shop shop = new Shop();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(3), 100.0, 20);
        boolean accepted = shop.accept(food);
        assertThat(accepted).isTrue();
        int percent = ExpirationCalculator.getExpirationPercentage(food);
        assertThat(percent).isEqualTo(75);
        assertThat(food.getPrice()).isEqualTo(100.0);
    }

    @Test
    void whenShelfLifeOfProductIs25PercentThanItShop() {
        Shop shop = new Shop();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(6), now.minusDays(2), 100.0, 20);
        boolean accepted = shop.accept(food);
        assertThat(accepted).isTrue();
        int percent = ExpirationCalculator.getExpirationPercentage(food);
        assertThat(percent).isEqualTo(25);
        assertThat(food.getPrice()).isEqualTo(100.0);
    }

}