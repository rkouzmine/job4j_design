package ru.job4j.ood.parking.model.parking;

import ru.job4j.ood.parking.model.car.Car;

public class ParkingService {
    private final ParkingPassengerCar passengerParking;
    private final ParkingTruckCar truckParking;

    public ParkingService(ParkingPassengerCar passengerParking, ParkingTruckCar truckParking) {
        this.passengerParking = passengerParking;
        this.truckParking = truckParking;
    }

    public boolean park(Car car) {
        boolean result = false;
        int size = car.getParkingSpaceSize();
        if (size == 1) {
            passengerParking.add(car);
            result = true;
        } else if (size > 1) {
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
        for (int i = 0; i <= places.size() - size && !result; i++) {
            boolean canFit = true;
            for (int j = 0; j < size; j++) {
                if (places.get(i + j) == null) {
                    canFit = false;
                    break;
                }
            }
            if (canFit) {
                for (int j = 0; j < size; j++) {
                    places.get(i + j).park(car);
                }
                result = true;
            }
        }
        return result;
    }
}
