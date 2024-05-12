package ru.job4j.map;

import java.util.*;

public class City {
    private String name;
    private int age;
    private double area;
    private long numberOfPeople;
    private List<String> attractions;

    public City(String name, int age, double area, long numberOfPeople, List<String> attractions) {
        this.name = name;
        this.age = age;
        this.area = area;
        this.numberOfPeople = numberOfPeople;
        this.attractions = attractions;
    }

    @Override
    public String toString() {
        return "City{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", area=" + area
                + ", numberOfPeople=" + numberOfPeople
                + ", attractions=" + attractions
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return age == city.age && Double.compare(area, city.area) == 0
                && numberOfPeople == city.numberOfPeople
                && Objects.equals(name, city.name)
                && Objects.equals(attractions, city.attractions);
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + Integer.hashCode(age);
        result = 31 * result + Double.hashCode(area);
        result = 31 * result + Long.hashCode(numberOfPeople);
        result = 31 * result + (attractions != null ? attractions.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        Map<City, Boolean> map = new HashMap<>();
        City cityFirst = new City(
                "Saint Petersburg", 320, 1439, 7000000,
                List.of("Palace Square", "State Hermitage Museum")
        );
        boolean isTouristFriendly = true;
        map.put(cityFirst, isTouristFriendly);
        System.out.println(map.get(new City("Saint Petersburg", 320, 1439, 7000000,
                List.of("Palace Square", "State Hermitage Museum"))));
    }
}
