package ru.job4j.ood.parking.model.car;

public class PassengerCar extends Car {

    public PassengerCar(String number) {
        super(number);
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    @Override
    public int getParkingSpaceSize() {
        return 1;
    }
}
