package ru.job4j.ood.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstorage.model.Food;
import ru.job4j.ood.foodstorage.store.AbstractStore;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AbstractStoreTest {


    static class TestStore extends AbstractStore {
        @Override
        public boolean accept(Food food) {
            return true;
        }
    }

    @Test
    void whenAddFoodToTheStore() {
        TestStore store = new TestStore();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(3), 100.0, 20);
        store.add(food);
        assertThat(store.getAll()).contains(food);

    }

    @Test
    void whenRemoveFoodFromTheStore() {
        TestStore store = new TestStore();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(3), 100.0, 20);
        store.add(food);
        store.remove(food);
        assertThat(store.getAll()).isEmpty();
    }

    @Test
    void whenFindByNameIsInTheStore() {
        TestStore store = new TestStore();
        LocalDate now = LocalDate.now();
        Food foodFirst = new Food("Food_1", now.plusDays(1), now.minusDays(3), 100.0, 20);
        Food foodSecond = new Food("Food_1", now.plusDays(1), now.minusDays(3), 150.0, 20);
        store.add(foodFirst);
        store.add(foodSecond);
        List<Food> result = store.findByName("Food_1");
        assertThat(result).containsExactly(foodFirst, foodSecond);
    }

    @Test
    void whenFindByNameThanNotFoundInTheStore() {
        TestStore store = new TestStore();
        LocalDate now = LocalDate.now();
        Food food = new Food("Food", now.plusDays(1), now.minusDays(3), 100.0, 20);
        store.add(food);
        assertThat(store.findByName("Not Found")).isEmpty();
    }

    @Test
    void whenRenameExistingFoodNameThanIsChanged() {
        TestStore store = new TestStore();
        LocalDate now = LocalDate.now();
        Food food = new Food("Name", now.plusDays(1), now.minusDays(3), 100.0, 20);
        store.add(food);
        store.rename(food, "NewName");
        assertThat(store.getAll()).extracting(Food::getName).containsExactly("NewName");

    }

}