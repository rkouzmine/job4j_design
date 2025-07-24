package ru.job4j.ood.ocp.drawable;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Drawable> rectangles = List.of(new Rectangle(), new Circle(), new Square());
        rectangles.forEach(d -> System.out.println(d.draw()));
    }
}
