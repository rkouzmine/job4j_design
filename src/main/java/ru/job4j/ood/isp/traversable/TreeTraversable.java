package ru.job4j.ood.isp.traversable;

import java.util.Iterator;

public interface TreeTraversable<T> {

    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();

}