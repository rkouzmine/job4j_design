package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.service.ControlQuality;
import ru.job4j.ood.foodstorage.store.Shop;
import ru.job4j.ood.foodstorage.store.Trash;
import ru.job4j.ood.foodstorage.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenDistributeFoodThanPlacedInCorrectStore() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        ControlQuality<Food> controlQuality = new ControlQuality<>(List.of(warehouse, shop, trash));

        LocalDate now = LocalDate.now();

        Food bread = new Food("Bread", now.plusDays(1), now.minusDays(4), 100.0, 20);
        Food milk = new Food("Milk", now.minusDays(1), now.minusDays(10), 50.0, 20);
        Food cheese = new Food("Cheese", now.plusDays(40), now.minusDays(1), 200.0, 20);
        Food yogurt = new Food("Yogurt", now.minusDays(5), now.minusDays(15), 15.0, 20);

        controlQuality.distribute(bread);
        controlQuality.distribute(milk);
        controlQuality.distribute(cheese);
        controlQuality.distribute(yogurt);

        assertThat(shop.getAll())
                .containsExactly(bread);

        assertThat(trash.getAll())
                .containsExactlyInAnyOrder(milk, yogurt);

        assertThat(warehouse.getAll())
                .containsExactly(cheese);
    }

}