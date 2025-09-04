package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.*;

public class ParkingPassengerCar implements Parking<Car> {

    private final List<ParkingPlace> places = new ArrayList<>();

    public ParkingPassengerCar(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("The number of parking spaces for passenger cars cannot be negative: " + size);
        }

        for (int i = 1; i <= size; i++) {
            places.add(new ParkingPlace(i));
        }
    }

    public List<ParkingPlace> getPlaces() {
        return places;
    }


    @Override
    public boolean add(Car car) {
        boolean result = false;
        for (ParkingPlace place : places) {
            if (place.getCar() == null) {
                place.park(car);
                result = true;
            }
        }
        return result;
    }

//    private boolean parkInFirstAvailable(List<ParkingPlace> places, Car car) {
//        boolean result = false;
//        for (ParkingPlace place : places) {
//            if (place.getCar() == null) {
//                place.park(car);
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
//
//    private boolean parkInFirstAvailableSequence(List<ParkingPlace> places, Car car, int size) {
//        boolean result = false;
//        for (int i = 0; i <= places.size() - size && !result; i++) {
//            boolean canFit = true;
//            for (int j = 0; j < size; j++) {
//                if (places.get(i + j).getCar() != null) {
//                    canFit = false;
//                    break;
//                }
//            }
//            if (canFit) {
//                for (int j = 0; j < size; j++) {
//                    places.get(i + j).park(car);
//                }
//                result = true;
//            }
//        }
//        return result;
//    }

    @Override
    public boolean remove(Car car) {
        boolean result = false;
        if (car.getParkingSpaceSize() == 1) {
            for (ParkingPlace place : places) {
                if (place.getCar() != null && place.getCar().equals(car)) {
                    place.release(car);
                    result = true;
                }
            }
        }
        return result;
    }

    public List<Car> getPassengerParkingSpaces(List<ParkingPlace> places) {
        List<Car> result = new ArrayList<>();
        for (ParkingPlace place : places) {
            if (place.getCar() != null) {
                result.add(place.getCar());
            }
        }
        return result;
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(new LinkedHashSet<>(getPassengerParkingSpaces(places)));
    }
}
