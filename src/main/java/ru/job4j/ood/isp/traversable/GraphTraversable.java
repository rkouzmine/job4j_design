package ru.job4j.ood.isp.traversable;

import java.util.Iterator;

public interface GraphTraversable<T> {

    Iterator<T> bfs();
    Iterator<T> dfs();

}