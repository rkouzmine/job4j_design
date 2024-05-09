package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NonCollisionMapTest {

    private final SimpleMap<Integer, String> map = new NonCollisionMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> iterator = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> iterator = map.iterator();
        map.get(1);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }


    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }

    @Test
    void whenCheckPutAndGet() {
        SimpleMap<Integer, String> simpleMap = new NonCollisionMap<>();
        simpleMap.put(1, "First");
        simpleMap.put(2, "Second");
        simpleMap.put(3, "Third");
        assertThat(simpleMap).hasSize(3).contains(1, 2, 3);
        assertThat(simpleMap.get(0)).isNull();
        assertThat(simpleMap.put(null, "Zero")).isTrue();
        simpleMap.put(1, "One");
        simpleMap.put(2, "Two");
        simpleMap.put(3, "Three");
        assertThat(simpleMap.get(null)).isEqualTo("Zero");
        assertThat(simpleMap).hasSize(4).contains(null, 1, 2, 3);
    }

    @Test
    void whenCheckPutAndPutIsFalse() {
        assertThat(map.put(null, "Zero")).isTrue();
        assertThat(map.put(null, "Zero")).isFalse();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetIsNull() {
        assertThat(map.get(10)).isNull();
    }

    @Test
    void whenGetExistingKey() {
        SimpleMap<String, Integer> simpleMap = new NonCollisionMap<>();
        simpleMap.put("First", 1);
        simpleMap.put("Second", 2);
        simpleMap.put("Third", 3);
        Integer result = simpleMap.get("First");
        assertEquals(1, result.intValue());
    }

    @Test
    void whenCheckRemoveAndRemoveIsFalse() {
        assertThat(map.remove(1)).isTrue();
        assertThat(map.remove(1)).isFalse();
    }

    @Test
    void whenCheckRemoveIsFalse() {
        assertThat(map.remove(10)).isFalse();
    }

    @Test
    void whenCheckRemoveNullIsFalse() {
        assertThat(map.remove(null)).isFalse();
    }

    @Test
    void whenTwoObjectsAreTheSame() {
        Calendar birthday = Calendar.getInstance();
        SimpleMap<User, Object> simpleMap = new NonCollisionMap<>();
        User userFirst = new User("user", 2, birthday);
        User userSecond = new User("user", 2, birthday);
        simpleMap.put(userFirst, new Object());
        simpleMap.put(userSecond, new Object());
        assertThat(simpleMap).hasSize(1);
        simpleMap.remove(userSecond);
        assertThat(simpleMap).isEmpty();
    }

    @Test
    void whenTwoObjectsAreDifferent() {
        Calendar birthday = Calendar.getInstance();
        SimpleMap<User, Object> simpleMap = new NonCollisionMap<>();
        User userFirst = new User("user", 0, birthday);
        User userSecond = new User("username", 0, birthday);
        simpleMap.put(userFirst, new Object());
        simpleMap.put(userSecond, new Object());
        assertThat(simpleMap).hasSize(2);
        simpleMap.remove(userSecond);
        assertThat(simpleMap).hasSize(1);
    }
}