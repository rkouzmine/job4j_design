package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private T[] arrayExpansion(T[] array) {
        int capacity = array.length;
        if (capacity == 0) {
            capacity = 10;
        } else {
            capacity *= 2;
        }
        return Arrays.copyOf(array, capacity);
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = arrayExpansion(container);
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T setValue = container[index];
        container[index] = newValue;
        return setValue;
    }

    @Override
    public T remove(int index) {
        modCount++;
        Objects.checkIndex(index, size);
        T removedElement = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1);
        size--;
        container[container.length - 1] = null;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}