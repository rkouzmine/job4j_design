package ru.job4j.ood.parking.model.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingPassengerCarTest {

    @Test
    public void whenNegativePassengerPlacesThenThrowException() {
        int sizeParkingPassengerCar = -1;
        assertThatThrownBy(() -> new ParkingPassengerCar(sizeParkingPassengerCar))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The number of parking spaces for passenger cars cannot be negative: " + sizeParkingPassengerCar);
    }

}