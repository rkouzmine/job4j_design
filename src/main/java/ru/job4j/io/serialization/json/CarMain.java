package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

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

        JSONObject jsonEngine = new JSONObject("{\"horsePower\":250}");

        List<String> listFeatures = new ArrayList<>();
        listFeatures.add("Anti-Stress Wheels");
        listFeatures.add("Joy Generator");
        listFeatures.add("Jack of All Trades Mode");
        JSONArray jsonArray = new JSONArray(listFeatures);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isElectric", car.isElectric());
        jsonObject.put("model", "Blue Tractor");
        jsonObject.put("year", car.getYear());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("features", jsonArray);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}