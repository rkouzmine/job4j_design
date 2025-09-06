package ru.job4j.ood.parking.model.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.car.Car;
import ru.job4j.ood.parking.model.car.PassengerCar;
import ru.job4j.ood.parking.model.car.TruckCar;

import static org.assertj.core.api.Assertions.*;

public class ParkingCarTest {

    @Test
    public void whenAddPassengerCarThenItIsInPassengerPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(4);
        ParkingTruckCar truckParking = new ParkingTruckCar(2);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCar = new PassengerCar("A001");
        parkingCar.add(passengerCar);
        assertThat(passengerParking.findAllCar()).containsExactly(passengerCar);
    }

    @Test
    public void whenAddTruckCarThenItIsInTruckPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(4);
        ParkingTruckCar truckParking = new ParkingTruckCar(2);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(parkingCar.findAllCar()).containsExactly(truckCar);
    }

    @Test
    public void whenAddTruckCarAndNoTruckPlacesThenUsePassengerPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(2);
        ParkingTruckCar truckParking = new ParkingTruckCar(0);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(parkingCar.findAllCar()).contains(truckCar);
    }


    @Test
    public void whenTruckAndPassengerCarsAddInPassengerPlaces() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(3);
        ParkingTruckCar truckParking = new ParkingTruckCar(1);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCar = new PassengerCar("A001");
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingCar.add(passengerCar);
        parkingCar.add(truckCarFirst);
        parkingCar.add(truckCarSecond);
        assertThat(passengerParking.findAllCar()).contains(passengerCar);
        assertThat(passengerParking.findAllCar()).contains(truckCarSecond);
    }

    @Test
    public void whenAddTruckCarAndNoSpaceAnywhereThenNotParked() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(1);
        ParkingTruckCar truckParking = new ParkingTruckCar(0);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCar = new TruckCar("A002", 2);
        parkingCar.add(truckCar);
        assertThat(parkingCar.getPassengerParking().findAllCar()).isEmpty();
        assertThat(parkingCar.getTruckParking().findAllCar()).isEmpty();
    }

    @Test
    public void whenAddTwoTruckCarsInPassengerPlacesThenBothParked() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(4);
        ParkingTruckCar truckParking = new ParkingTruckCar(0);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car truckCarFirst = new TruckCar("A002", 2);
        Car truckCarSecond = new TruckCar("A003", 2);
        parkingCar.add(truckCarFirst);
        parkingCar.add(truckCarSecond);
        assertThat(passengerParking.findAllCar()).contains(truckCarFirst);
        assertThat(passengerParking.findAllCar()).contains(truckCarSecond);
    }

    @Test
    public void whenAddAndRemoveCarsThenParkingIsEmpty() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(1);
        ParkingTruckCar truckParking = new ParkingTruckCar(3);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCar = new PassengerCar("A001");
        Car truckCar = new TruckCar("A002", 3);
        parkingCar.add(passengerCar);
        parkingCar.add(truckCar);
        assertThat(parkingCar.findAllCar()).contains(passengerCar);
        assertThat(parkingCar.findAllCar()).contains(truckCar);
        parkingCar.remove(passengerCar);
        parkingCar.remove(truckCar);
        assertThat(parkingCar.findAllCar()).isEmpty();
    }

    @Test
    public void whenPassengerAndTruckCarsThenConditionOfParkingStateCorrect() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(8);
        ParkingTruckCar truckParking = new ParkingTruckCar(1);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCarFirst = new PassengerCar("A001");
        Car passengerCarSecond = new PassengerCar("A002");
        Car truckCarFirst = new TruckCar("A003", 2);
        Car truckCarSecond = new TruckCar("A004", 3);
        parkingCar.add(passengerCarFirst);
        parkingCar.add(passengerCarSecond);
        parkingCar.add(truckCarFirst);
        parkingCar.add(truckCarSecond);
        String result = parkingCar.toString();
        String expected = "ParkingCar{passengerParking=AbstractParking{places=["
                + "ParkingPlace{placeId=1, car=Car{number='A001', parkingSpaceSize=1}}, ParkingPlace{placeId=2, car=Car{number='A002', parkingSpaceSize=1}}, "
                + "ParkingPlace{placeId=3, car=Car{number='A004', parkingSpaceSize=3}}, ParkingPlace{placeId=4, car=Car{number='A004', parkingSpaceSize=3}}, "
                + "ParkingPlace{placeId=5, car=Car{number='A004', parkingSpaceSize=3}}, [empty], [empty], [empty]]}, "
                + "truckParking=AbstractParking{places=["
                + "ParkingPlace{placeId=1, car=Car{number='A003', parkingSpaceSize=2}}]}}";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenGetAllCarsThenReturnExpectedOrder() {
        ParkingPassengerCar passengerParking = new ParkingPassengerCar(5);
        ParkingTruckCar truckParking = new ParkingTruckCar(1);
        ParkingCar parkingCar = new ParkingCar(passengerParking, truckParking);
        Car passengerCarFirst = new PassengerCar("A001");
        Car passengerCarSecond = new PassengerCar("A002");
        Car truckCarFirst = new TruckCar("A003", 2);
        Car truckCarSecond = new TruckCar("A004", 3);
        parkingCar.add(passengerCarFirst);
        parkingCar.add(passengerCarSecond);
        parkingCar.add(truckCarFirst);
        parkingCar.add(truckCarSecond);
        String result = parkingCar.findAllCar().toString();
        String expected = "[Car{number='A001', parkingSpaceSize=1}, "
                + "Car{number='A002', parkingSpaceSize=1}, "
                + "Car{number='A004', parkingSpaceSize=3}, "
                + "Car{number='A003', parkingSpaceSize=2}]";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenTruckNeedsConsecutiveSpacesButTheyAreFragmented() {
        ParkingPassengerCar parkingPassengerCar = new ParkingPassengerCar(3);
        ParkingTruckCar parkingTruckCar = new ParkingTruckCar(0);
        ParkingCar parkingCar = new ParkingCar(parkingPassengerCar, parkingTruckCar);
        PassengerCar passengerCarFirst = new PassengerCar("A001");
        PassengerCar passengerCarSecond = new PassengerCar("A002");
        TruckCar truckCar = new TruckCar("T001", 2);
        parkingCar.add(passengerCarFirst);
        parkingCar.add(passengerCarSecond);
        parkingCar.remove(passengerCarFirst);
        String result = parkingCar.toString();
        assertThat(parkingCar.add(truckCar)).isFalse();
        String expected = "ParkingCar{passengerParking=AbstractParking{places=["
                + "[empty], "
                + "ParkingPlace{placeId=2, car=Car{number='A002', parkingSpaceSize=1}}, "
                + "[empty]]}, truckParking=AbstractParking{places=[]}}";
        assertThat(result).isEqualTo(expected);
    }

}