package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean isUnique = true;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                set.add(value);
                isUnique = false;
                break;
            }
        }
        if (isUnique) {
            set.add(value);
        }
        return isUnique;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}