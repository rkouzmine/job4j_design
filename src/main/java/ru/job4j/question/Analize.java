package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }

        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }

        int addedCount = 0;
        int changedCount = 0;
        int deletedCount = 0;

        for (User user : current) {
            if (previousMap.get(user.getId()) == null) {
                addedCount++;
            } else if (!previousMap.containsValue(user.getName())) {
                changedCount++;
            }
        }

        for (User user : previous) {
            if (!currentMap.containsKey(user.getId())) {
                deletedCount++;
            }
        }
        return new Info(addedCount, changedCount, deletedCount);
    }

}