package ru.job4j.pool;

public class StringPoolExample {
    public static void main(String[] args) {
        String string1 = new String("Hello");
        String string2 = new String("Hello");
        String string3 = "Hello";
        String string4 = "Hello";
        System.out.println(string1 == string2);
        System.out.println(string3 == string4);
        System.out.println(string1 == string3);
        System.out.println(string2 == string4);

        System.out.println();

        String string5 = "Hello, world";
        String string6 = "Hello, " + "world";
        System.out.println(string5 == string6);

        System.out.println();

        String string8 = "Hello, world";
        String string9 = "Hello, ";
        String string10 = string9 + "world";
        System.out.println(string8 == string10);

        System.out.println();

        String string11 = "Hello";
        String string12 = new String("Hello");
        String string13 = string12.intern();
        System.out.println(string11 == string13);

        System.out.println();

        System.out.println(new String("New string") == new String("New string"));
        System.out.println(new String("Interned string").intern() == new String("Interned string").intern());
    }
}