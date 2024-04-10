package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ArrayIteratorTest {

    @Test
    void whenMultiCallhasNextThenTrue() {
        ArrayIterator iterator = new ArrayIterator(
                new int[] {1, 2, 3}
        );
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
    }

    @Test
    void whenReadSequence() {
        ArrayIterator iterator = new ArrayIterator(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }
}