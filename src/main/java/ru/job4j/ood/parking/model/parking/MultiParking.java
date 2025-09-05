//package ru.job4j.ood.parking.model.parking;
//
//import ru.job4j.ood.parking.model.car.Car;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MultiParking extends AbstractParking {
//    private ParkingPassengerCar passengerParking;
//    private ParkingTruckCar truckParking;
//
//    public MultiParking(ParkingPassengerCar passengerParking, ParkingTruckCar truckParking) {
//        this.passengerParking = passengerParking;
//        this.truckParking = truckParking;
//    }
//
//    @Override
//    public boolean add(Car car) {
//        if (car.getParkingSpaceSize() == 1) {
//            // Легковая машина
//            return passengerParking.add(car);
//        } else {
//            // Грузовая машина
//            return truckParking.add(car) || parkTruckInPassenger(car);
//        }
//    }
//
//    private boolean parkTruckInPassenger(Car car) {
//        int size = car.getParkingSpaceSize();
//        var places = passengerParking.getPlaces();
//
//        for (int i = 0; i <= places.size() - size; i++) {
//            boolean canFit = true;
//            for (int j = 0; j < size; j++) {
//                if (!places.get(i + j).isFree()) {
//                    canFit = false;
//                    break;
//                }
//            }
//            if (canFit) {
//                for (int j = 0; j < size; j++) {
//                    places.get(i + j).park(car);
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean remove(Car car) {
//        return passengerParking.remove(car) || truckParking.remove(car);
//    }
//
//    @Override
//    public List<Car> getAllCars() {
//        List<Car> all = new ArrayList<>();
//        all.addAll(passengerParking.getAllCars());
//        all.addAll(truckParking.getAllCars());
//        return all;
//    }
//}
