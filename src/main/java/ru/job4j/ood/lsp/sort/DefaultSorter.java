package ru.job4j.ood.lsp.sort;

import java.util.Collections;
import java.util.List;

public class DefaultSorter implements Sorter {
    public List<Integer> sort(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers;
    }
}