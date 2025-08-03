package ru.job4j.ood.lsp.stringlength;

public class DefaultLength implements StringLength {
    public int length(String s) {
        return s.trim().length();
    }
}