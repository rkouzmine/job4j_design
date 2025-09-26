package ru.job4j.ood.foodstorage.service;

import ru.job4j.ood.foodstorage.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality<T> {
    private final List<Store<T>> stores;

    public ControlQuality(List<Store<T>> stores) {
        this.stores = stores;
    }

    public void distribute(T t) {
        for (Store<T> store : stores) {
            if (store.accept(t)) {
                store.add(t);
                break;
            }
        }
    }

    public void resort() {
        List<T> list = new ArrayList<>();
        for (Store<T> store : stores) {
            list.addAll(store.getAll());
            store.clear();
        }

        for (T t : list) {
            distribute(t);
        }
    }
}
