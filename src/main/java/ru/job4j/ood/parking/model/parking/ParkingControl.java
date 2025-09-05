//package ru.job4j.ood.parking.model.parking;
//
//import java.util.List;
//
//public class ParkingControl<T> {
//    private final List<Parking<T>> parkings;
//
//    public ParkingControl(List<Parking<T>> parkings) {
//        this.parkings = parkings;
//    }
//
//    public void distribute(T t) {
//        for (Parking<T> parking : parkings) {
//            if (parking.canAccept(t)) {
//                parking.add(t);
//                break;
//            }
//        }
//    }
//}
