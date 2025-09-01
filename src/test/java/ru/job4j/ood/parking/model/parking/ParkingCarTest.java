package ru.job4j.ood.parking.model.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.car.Car;
import ru.job4j.ood.parking.model.car.PassengerCar;
import ru.job4j.ood.parking.model.car.TruckCar;

import static org.assertj.core.api.Assertions.*;

@Disabled("Удалить аннотацию после реализации всех методов")
public class ParkingCarTest {

    @Test
    public void whenAddPassengerCarThenItIsInPassengerPlaces() {
        ParkingCar parking = new ParkingCar(4, 2);
        Car passengerCar = new PassengerCar("A001");
        parking.add(passengerCar);
        assertThat(parking.getAll()).containsExactly(passengerCar);
    }

    @Test
    public void whenAddTruckCarThenItIsInTruckPlaces() {
        ParkingCar parking = new ParkingCar(4, 2);
        Car truckCar = new TruckCar("A002", 2);
        parking.add(truckCar);
        assertThat(parking.getAll()).containsExactly(truckCar);
    }

    @Test
    public void whenAddTruckCarAndNoTruckPlacesThenUsePassengerPlaces() {
        ParkingCar parking = new ParkingCar(2, 0);
        Car truckCar = new TruckCar("A002", 2);
        parking.add(truckCar);
        assertThat(parking.getAll()).containsExactly(truckCar);
    }

    @Test
    public void whenTruckCarsAddInPassengerPlaces() {
        ParkingCar parking = new ParkingCar(3, 2);
        Car passengerCar = new PassengerCar("A001");
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parking.add(passengerCar);
        parking.add(truckCarFirst);
        parking.add(truckCarSecond);
        var result = parking.getCarsFromParkingSpaces(parking.getPassengerPlaces());
        assertThat(result).containsExactly(truckCarSecond);
    }

    @Test
    public void whenAddTruckCarAndNotEnoughPassengerPlacesThenFail() {
        ParkingCar parking = new ParkingCar(1, 0);
        Car truckCar = new TruckCar("A002", 2);
        parking.add(truckCar);
        assertThat(parking.getAll()).isEmpty();
    }

    @Test
    public void whenAddAndRemoveCarsThenParkingIsEmpty() {
        ParkingCar parking = new ParkingCar(1, 2);
        Car passengerCar = new PassengerCar("A001");
        Car truckCar = new TruckCar("A002", 2);
        parking.add(passengerCar);
        parking.add(truckCar);
        assertThat(parking.getAll()).containsExactly(passengerCar);
        assertThat(parking.getAll()).containsExactly(truckCar);
        parking.remove(passengerCar);
        parking.remove(truckCar);
        assertThat(parking.getAll()).isEmpty();
    }

}