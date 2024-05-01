package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Calendar;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User userFirst = new User("user", 2, birthday);
        User userSecond = new User("user", 2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        System.out.println(map);
        int hashCodeFirst = userFirst.hashCode();
        int hashFirst = hashCodeFirst ^ (hashCodeFirst >>> 16);
        int bucketFirst = hashFirst & 15;
        int hashCodeSecond = userSecond.hashCode();
        int hashSecond = hashCodeSecond ^ (hashCodeSecond >>> 16);
        int bucketSecond = hashSecond & 15;
        System.out.printf("userFirst - хэшкод: %s, хэш: %s, бакет: %s\n", hashCodeFirst, hashFirst, bucketFirst);
        System.out.printf("userSecond - хэшкод: %s, хэш: %s, бакет: %s\n", hashCodeSecond, hashSecond, bucketSecond);
    }
}
