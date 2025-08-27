package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.store.Warehouse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    void whenExpirationLessThan25PercentThenAcceptedToWarehouse() {
        Warehouse warehouse = new Warehouse();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(6), now.minusDays(1), 100.0, 20);
        boolean accepted = warehouse.accept(food);
        assertThat(accepted).isTrue();
    }

}