package ru.job4j.ood.dip.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.dip.delivery.courier.BikeCourier;
import ru.job4j.ood.dip.delivery.courier.CarCourier;
import ru.job4j.ood.dip.delivery.courier.Courier;
import ru.job4j.ood.dip.delivery.model.OnlineOrder;
import ru.job4j.ood.dip.delivery.model.Order;
import ru.job4j.ood.dip.delivery.model.Product;
import ru.job4j.ood.dip.delivery.service.CourierService;
import ru.job4j.ood.dip.delivery.service.OrderCalculator;
import ru.job4j.ood.dip.delivery.service.SimpleOrderCalculator;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CourierServiceTest {
    private Order lightOrder;
    private Order heavyOrder;
    private OrderCalculator calculator;
    private Courier bikeCourier;
    private Courier carCourier;
    private CourierService courierService;

    @BeforeEach
    void setup() {
        calculator = new SimpleOrderCalculator();
        bikeCourier = new BikeCourier(calculator);
        carCourier = new CarCourier(calculator);
        courierService = new CourierService(List.of(bikeCourier, carCourier));

        lightOrder = new OnlineOrder();
        lightOrder.add(new Product("Книга", 500, 1.2));
        lightOrder.add(new Product("Блокнот", 300, 0.5));

        heavyOrder = new OnlineOrder();
        heavyOrder.add(new Product("Ноутбук", 50000, 2.5));
        heavyOrder.add(new Product("Монитор", 12000, 4.0));
    }

    @Test
    void whenDistributeLightOrderThenBikeCourierDelivers() {
        assertThat(bikeCourier.accept(lightOrder)).isTrue();
        assertThat(carCourier.accept(lightOrder)).isTrue();
        courierService.distribute(lightOrder);
    }

    @Test
    void whenDistributeHeavyOrderThenCarCourierDelivers() {
        assertThat(bikeCourier.accept(heavyOrder)).isFalse();
        assertThat(carCourier.accept(heavyOrder)).isTrue();
        courierService.distribute(heavyOrder);
    }

    @Test
    void whenOrderTooHeavyThenNoCourierDelivers() {
        Order hugeOrder = new OnlineOrder();
        hugeOrder.add(new Product("Сейф", 100000, 500));
        assertThat(bikeCourier.accept(hugeOrder)).isFalse();
        assertThat(carCourier.accept(hugeOrder)).isFalse();
        courierService.distribute(hugeOrder);
    }

    @Test
    void testOrderTotalWeightCalculation() {
        double lightWeight = calculator.calculateTotalWeight(lightOrder);
        double heavyWeight = calculator.calculateTotalWeight(heavyOrder);
        assertThat(lightWeight).isEqualTo(1.2 + 0.5);
        assertThat(heavyWeight).isEqualTo(2.5 + 4.0);
    }

}