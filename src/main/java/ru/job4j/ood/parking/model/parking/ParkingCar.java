package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

import java.util.ArrayList;
import java.util.List;

public class ParkingCar implements Parking<Car> {

    private ParkingPassengerCar passengerParking;
    private ParkingTruckCar truckParking;

    public ParkingCar(ParkingPassengerCar passengerParking, ParkingTruckCar truckParking) {
        this.passengerParking = passengerParking;
        this.truckParking = truckParking;
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.getParkingSpaceSize() == 1) {
            // Легковая машина
            result = passengerParking.add(car);
        } else {
            // Грузовая машина
            result = truckParking.add(car) || passengerParking.add(car);
        }
        return result;
    }

    private boolean tryParkTruckInPassenger(Car car) {
        int size = car.getParkingSpaceSize();
        var places = passengerParking.getPlaces();

        for (int i = 0; i <= places.size() - size; i++) {
            boolean canFit = true;
            for (int j = 0; j < size; j++) {
                if (!places.get(i + j).isFree()) { // или places.get(i + j).getCar() != null
                    canFit = false;
                    break;
                }
            }
            if (canFit) {
                for (int j = 0; j < size; j++) {
                    places.get(i + j).park(car);
                }
                return true; // сразу возвращаем, машина добавлена один раз
            }
        }
        return false;
    }

    @Override
    public boolean remove(Car car) {
        return passengerParking.remove(car) || truckParking.remove(car);
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> all = new ArrayList<>();
        all.addAll(passengerParking.getAllCars());
        all.addAll(truckParking.getAllCars());
        return all;
    }
}