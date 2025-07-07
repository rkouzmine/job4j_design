package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    public void whenAllKeysPresentThenReturnFilledTemplate() {
        Generator generator = new GeneratorString();
        Map<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? ";
        String expected = "I am a Petr Arsentev, Who are you? ";
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMapMissingKeyGetException() {
        Generator generator = new GeneratorString();
        Map<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? ";
        args.put("name", "Job4j");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapHasExtraKeyGetException() {
        Generator generator = new GeneratorString();
        Map<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? ";
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("age", "20");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
