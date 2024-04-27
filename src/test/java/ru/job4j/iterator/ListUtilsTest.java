package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfEvenNumbers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> filter = n -> n % 2 == 0;
        ListUtils.removeIf(list, filter);
        assertThat(list).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenReplaceIfCWithCPlusPlus() {
        Predicate<String> filter = n -> n.startsWith("C");
        List<String> list = new ArrayList<>(Arrays.asList("Java", "C", "Kotlin"));
        String value = "C++";
        ListUtils.replaceIf(list, filter, value);
        assertThat(list).containsSequence("Java", "C++", "Kotlin");
    }

    @Test
    void whenRemoveAllElementsThatAreOnTheList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 5, 7));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4, 5, 6));
        ListUtils.removeAll(list, elements);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 3, 7));
        assertThat(list).isEqualTo(expected);
    }

    @Test
    void whenRemoveAllListIsNull() {
        List<Integer> list = null;
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4, 5, 6));
        assertThatThrownBy(() -> ListUtils.removeAll(list, elements))
                .isInstanceOf(IllegalArgumentException.class);
    }

}