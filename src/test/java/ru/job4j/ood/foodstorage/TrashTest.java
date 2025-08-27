package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.store.Trash;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    void whenFoodIsExpiredThenTrashAccepts() {
        Trash trash = new Trash();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now, now.minusDays(3), 100.0, 20);
        boolean accepted = trash.accept(food);
        assertThat(accepted).isTrue();
    }

}