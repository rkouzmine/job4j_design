package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 20;
        long money = 10005000L;
        double distance = 1551.4242D;
        float weight = 8.0F;
        byte memory = 0;
        short size = 1024;
        char symbol = '+';
        boolean isNotBad = true;
        LOG.debug("Variables : age - {}, money - {}, distance - {}, weight - {}, memory - {}, size - {}, symbol - {}, isNotBad - {}",
                age, money, distance, weight, memory, size, symbol, isNotBad);
    }
}