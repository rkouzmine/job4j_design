package ru.job4j.loader;

public class Loader {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> loader = Loader.class;
        System.out.println("Класс переменной loader: " + loader);
        System.out.println("Загрузчик класса переменной loader:  " + loader.getClassLoader());
    }

}