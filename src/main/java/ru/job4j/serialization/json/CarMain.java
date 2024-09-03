package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CarMain {
    public static void main(String[] args) {
        Car car = new Car(false, "Geely Coolray", 2022,
                new Engine(170), new String[]{"Air Conditioning", "Navigation System"});

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"isElectric\": true,"
                        + "\"model\":\"Blue Tractor\","
                        + "\"year\": 2024,"
                        + "\"engine\":"
                        + "{"
                        + "\"horsePower\": 250"
                        + "},"
                        + "\"features\":"
                        + "[\"Endless Fuel Tank\",\"Field Party Mode\"]"
                        + "}";

       final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}