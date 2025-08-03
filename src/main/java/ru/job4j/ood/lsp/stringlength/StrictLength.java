package ru.job4j.ood.lsp.stringlength;

public class StrictLength implements StringLength {
    public int length(String s) {
        if (s == null || s.trim().length() < 2) {
            throw new IllegalArgumentException();
        }
        return s.trim().length();
    }
}