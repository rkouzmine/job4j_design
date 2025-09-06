package ru.job4j.ood.parking.model.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingTruckCarTest {

    @Test
    public void whenNegativeTruckPlacesThenThrowException() {
        int sizeParkingTruckCar = -1;
        assertThatThrownBy(() -> new ParkingTruckCar(sizeParkingTruckCar))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The number of parking spaces for truck cars cannot be negative: " + sizeParkingTruckCar);
    }

}