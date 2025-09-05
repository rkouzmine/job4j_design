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
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(4);
        ParkingTruckCar truckParking = new ParkingTruckCar(2);
        Parking<Car> parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCar = new PassengerCar("A001");
        parkingCar.add(passengerCar);
        assertThat(passengerParking.getAllCars()).containsExactly(passengerCar);
    }

    @Test
    public void whenAddTruckCarThenItIsInTruckPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(4);
        ParkingTruckCar truckParking = new ParkingTruckCar(2);
        Parking<Car> parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(truckParking.getAllCars()).containsExactly(truckCar);
    }

    @Test
    public void whenAddTruckCarAndNoTruckPlacesThenUsePassengerPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(2);
        ParkingTruckCar truckParking = new ParkingTruckCar(0);
        Parking<Car> parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(passengerParking.getAllCars()).containsExactly(truckCar);
    }


    @Test
    public void whenTruckAndPassengerCarsAddInPassengerPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(3);
        ParkingTruckCar truckParking = new ParkingTruckCar(1);
        Parking<Car> parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCar = new PassengerCar("A001");
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingCar.add(passengerCar);
        parkingCar.add(truckCarFirst);
        parkingCar.add(truckCarSecond);
        assertThat(passengerParking.getAllCars()).contains(passengerCar);
        assertThat(passengerParking.getAllCars()).contains(truckCarSecond);
    }

    @Test
    public void whenAddTruckCarAndNotEnoughPassengerPlacesThenFail() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(1);
        ParkingTruckCar truckParking = new ParkingTruckCar(0);
        Parking<Car> parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(passengerParking.getAllCars()).isEmpty();
        assertThat(truckParking.getAllCars()).isEmpty();
    }
   /*
    @Test
    public void whenAddTruckCarAndNotEnoughPassengerPlacesThenFail2() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(4);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(0);
        ParkingCar parkingService = new ParkingCar(parkingPassengerCar, parkingTruckCar);
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingService.park(truckCarFirst);
        parkingService.park(truckCarSecond);
        assertThat(parkingPassengerCar.getAllCars()).contains(truckCarFirst);
        assertThat(parkingPassengerCar.getAllCars()).contains(truckCarSecond);
    }

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