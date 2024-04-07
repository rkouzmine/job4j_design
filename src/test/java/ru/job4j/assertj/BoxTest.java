package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .endsWith("dron")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isNotBlank()
                .contains("ub")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(6, 0);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .startsWith("Unknown")
                .isEqualTo("Unknown object");
    }

    @Test
    void isThisPositive() {
        Box box = new Box(8, 5);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isPositive()
                .isLessThan(10)
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void isThisNegative() {
        Box box = new Box(5, 8);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void whenExistIsTrue() {
        Box box = new Box(0, 5);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenExistIsFalse() {
        Box box = new Box(4, 0);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenAreaIsTrue() {
        Box box = new Box(4, 5);
        double result = box.getArea();
        assertThat(result)
                .isEqualTo(43.30d, withPrecision(0.01d))
                .isCloseTo(43.33d, withPrecision(0.03d))
                .isGreaterThan(43.28)
                .isLessThan(43.32);
    }

    @Test
    void whenAreaIsFalse() {
        Box box = new Box(6, 6);
        double result = box.getArea();
        assertThat(result).isEqualTo(0.0);
    }
}