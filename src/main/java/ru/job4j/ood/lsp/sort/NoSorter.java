package ru.job4j.ood.lsp.sort;

import java.util.List;

public class NoSorter implements Sorter {
    public List<Integer> sort(List<Integer> numbers) {
        return numbers;
    }
}