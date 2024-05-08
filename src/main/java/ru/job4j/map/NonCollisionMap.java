package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int getIndex(K key) {
        int hash = hash(Objects.hashCode(key));
        return indexFor(hash);
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int indexArray = getIndex(key);
        boolean isEmpty = table[indexArray] == null;
        if (isEmpty) {
            table[indexArray] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return isEmpty;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int newIndexArray = getIndex(mapEntry.key);
                newTable[newIndexArray] = mapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int indexArray = getIndex(key);
        boolean isFound = table[indexArray] != null
                && Objects.equals(table[indexArray].key, key);
        V value = null;
        if (isFound) {
            value = table[indexArray].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int indexArray = getIndex(key);
        boolean isFound = table[indexArray] != null
                && Objects.equals(table[indexArray].key, key);
        if (isFound) {
            table[indexArray] = null;
            count--;
            modCount++;
        }
        return isFound;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}