package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private Map<Integer, Post> posts = new HashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        var id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public void removeById(int id) {
        posts.remove(id);
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}