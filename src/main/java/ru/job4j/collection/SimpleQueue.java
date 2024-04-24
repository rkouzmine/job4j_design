package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int sizeInput;
    int sizeOutput;

    public T poll() {
        if (sizeOutput == 0) {
            while (sizeInput > 0) {
                output.push(input.pop());
                sizeInput--;
                sizeOutput++;
            }
        }
        if (sizeOutput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        sizeOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}