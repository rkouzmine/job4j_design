package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int cursor;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (cursor == data.size()) {
            cursor = 0;
        }
        return cursor < data.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(cursor++);
    }
}
