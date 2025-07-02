package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenExpectedAnswerThenFizz() {
        String result = Fool.expectedAnswer(3);
        String expected = "Fizz";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenExpectedAnswerThenBuzz() {
        String result = Fool.expectedAnswer(5);
        String expected = "Buzz";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenExpectedAnswerFizzBuzz() {
        String result = Fool.expectedAnswer(15);
        String expected = "FizzBuzz";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenExpectedAnswerThenReturnNumber() {
        assertThat(Fool.expectedAnswer(11)).isEqualTo(String.valueOf(11));
    }

}