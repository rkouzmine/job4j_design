package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int getHash(K key) {
        int hash = hash(Objects.hashCode(key));
        return indexFor(hash);
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int hash = getHash(key);
        int indexArray = indexFor(hash);
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
                int hash = getHash(mapEntry.key);
                int newIndexArray = indexFor(hash);
                newTable[newIndexArray] = mapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        int indexArray = indexFor(hash);
        boolean isFound = table[indexArray] != null;
        V value = null;
        if (isFound) {
            K currentKey = table[indexArray].key;
            if (Objects.equals(currentKey, key)) {
                value = table[indexArray].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int hash = getHash(key);
        int indexArray = indexFor(hash);
        boolean isFound = table[indexArray] != null;
        if (isFound) {
            K currentKey = table[indexArray].key;
            if (Objects.equals(currentKey, key)) {
                table[indexArray] = null;
                count--;
                modCount++;
            }
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

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));

        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}