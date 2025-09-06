package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ParkingCar implements Parking<Car> {

    private final ParkingPassengerCar passengerParking;
    private final ParkingTruckCar truckParking;

    public ParkingCar(ParkingPassengerCar passengerParking, ParkingTruckCar truckParking) {
        this.passengerParking = passengerParking;
        this.truckParking = truckParking;
    }

    public ParkingPassengerCar getPassengerParking() {
        return passengerParking;
    }

    public ParkingTruckCar getTruckParking() {
        return truckParking;
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.getParkingSpaceSize() == 1) {
            result = passengerParking.add(car);
        } else if (car.getParkingSpaceSize() > 1) {
            result = truckParking.add(car);
            if (!result) {
                result = tryParkTruckInPassenger(car);
            }
        }
        return result;
    }

    private boolean tryParkTruckInPassenger(Car car) {
        boolean result = false;
        int size = car.getParkingSpaceSize();
        var places = passengerParking.getPlaces();
        for (int i = 0; i <= places.size() - size; i++) {
            int j = 0;
            while (j < size && places.get(i + j).isFree()) {
                j++;
            }
            if (j == size) {
                for (int k = 0; k < size; k++) {
                    places.get(i + k).park(car);
                }
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Car car) {
        return car.getParkingSpaceSize() == 1 ? passengerParking.remove(car) : truckParking.remove(car);
    }

    @Override
    public List<Car> findAllCar() {
        Set<Car> result = new LinkedHashSet<>();
        result.addAll(passengerParking.findAllCar());
        result.addAll(truckParking.findAllCar());
        return new ArrayList<>(result);
    }

    @Override
    public String toString() {
        return "ParkingCar{"
                + "passengerParking=" + passengerParking
                + ", truckParking=" + truckParking
                + '}';
    }
}