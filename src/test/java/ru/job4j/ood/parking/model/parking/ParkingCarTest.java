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
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(4);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(2);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car passengerCar = new PassengerCar("A001");
        parkingService.park(passengerCar);
        assertThat(parkingPassengerCar.getAllCars()).containsExactly(passengerCar);
    }

    @Test
    public void whenAddTruckCarThenItIsInTruckPlaces() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(4);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(2);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car truckCar = new TruckCar("A002", 2);
        parkingService.park(truckCar);
        assertThat(parkingTruckCar.getAllCars()).containsExactly(truckCar);
    }

    @Test
    public void whenAddTruckCarAndNoTruckPlacesThenUsePassengerPlaces() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(2);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(0);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car truckCar = new TruckCar("A002", 2);
        parkingService.park(truckCar);
        assertThat(parkingPassengerCar.getAllCars()).containsExactly(truckCar);
    }

    @Test
    public void whenTruckAndPassengerCarsAddInPassengerPlaces() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(3);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(1);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car passengerCar = new PassengerCar("A001");
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingService.park(passengerCar);
        parkingService.park(truckCarFirst);
        parkingService.park(truckCarSecond);
        assertThat(parkingPassengerCar.getAllCars()).contains(passengerCar);
        assertThat(parkingPassengerCar.getAllCars()).contains(truckCarSecond);
    }

    @Test
    public void whenAddTruckCarAndNotEnoughPassengerPlacesThenFail() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(1);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(0);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car truckCar = new TruckCar("A002", 2);
        parkingService.park(truckCar);
        assertThat(parkingPassengerCar.getAllCars()).isEmpty();
        assertThat(parkingTruckCar.getAllCars()).isEmpty();
    }

    @Test
    public void whenAddTruckCarAndNotEnoughPassengerPlacesThenFail2() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(4);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(0);
        ParkingService parkingService = new ParkingService(parkingPassengerCar, parkingTruckCar);
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingService.park(truckCarFirst);
        parkingService.park(truckCarSecond);
        assertThat(parkingPassengerCar.getAllCars()).contains(truckCarFirst);
        assertThat(parkingPassengerCar.getAllCars()).contains(truckCarSecond);
    }
    /*
    @Test
    public void whenAddAndRemoveCarsThenParkingIsEmpty() {
        ParkingPassengerCar parking = new ParkingPassengerCar(1, 3);
        Car passengerCar = new PassengerCar("A001");
        Car truckCar = new TruckCar("A002", 3);
        parking.add(passengerCar);
        parking.add(truckCar);
        assertThat(parking.getAllCars()).contains(passengerCar);
        assertThat(parking.getAllCars()).contains(truckCar);
        parking.remove(passengerCar);
        parking.remove(truckCar);
        assertThat(parking.getAllCars()).isEmpty();
    }

    @Test
    public void whenConditionOfParkingSpaces() {
        ParkingPassengerCar parking = new ParkingPassengerCar(5, 1);
        Car passengerCarFirst = new PassengerCar("A001");
        Car passengerCarSecond = new PassengerCar("A002");
        Car truckCarFirst = new TruckCar("A003", 2);
        Car truckCarSecond = new TruckCar("A004", 3);
        parking.add(passengerCarFirst);
        parking.add(passengerCarSecond);
        parking.add(truckCarFirst);
        parking.add(truckCarSecond);
        String result = parking.toString();
        String expected = "ParkingCar{sizeFirst=5, sizeSecond=1, "
                + "passengerPlaces=["
                + "ParkingPlace{placeId=1, car=Car{number='A001', parkingSpaceSize=1}}, "
                + "ParkingPlace{placeId=2, car=Car{number='A002', parkingSpaceSize=1}}, "
                + "ParkingPlace{placeId=3, car=Car{number='A004', parkingSpaceSize=3}}, "
                + "ParkingPlace{placeId=4, car=Car{number='A004', parkingSpaceSize=3}}, "
                + "ParkingPlace{placeId=5, car=Car{number='A004', parkingSpaceSize=3}}], "
                + "truckPlaces=["
                + "ParkingPlace{placeId=1, car=Car{number='A003', parkingSpaceSize=2}}]}";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenGetAllCarsThenReturnUniqueCarsInExpectedOrder() {
        ParkingPassengerCar parking = new ParkingPassengerCar(5, 1);
        Car passengerCarFirst = new PassengerCar("A001");
        Car passengerCarSecond = new PassengerCar("A002");
        Car truckCarFirst = new TruckCar("A003", 2);
        Car truckCarSecond = new TruckCar("A004", 3);
        parking.add(passengerCarFirst);
        parking.add(passengerCarSecond);
        parking.add(truckCarFirst);
        parking.add(truckCarSecond);
        String result = parking.getAllCars().toString();
        String expected = "[Car{number='A001', parkingSpaceSize=1}, "
                + "Car{number='A002', parkingSpaceSize=1}, "
                + "Car{number='A004', parkingSpaceSize=3}, "
                + "Car{number='A003', parkingSpaceSize=2}]";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenNegativePassengerPlacesThenThrowException() {
        int sizeFirst = -1;
        int sizeSecond = 2;
        assertThatThrownBy(() -> new ParkingPassengerCar(sizeFirst, sizeSecond))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The number of parking spaces for passenger cars cannot be negative: " + sizeFirst);
    }

    @Test
    public void whenNegativeTruckPlacesThenThrowException() {
        int sizeFirst = 2;
        int sizeSecond = -1;
        assertThatThrownBy(() -> new ParkingPassengerCar(sizeFirst, sizeSecond))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The number of parking spaces for truck cars cannot be negative: " + sizeSecond);
    }
     */
}