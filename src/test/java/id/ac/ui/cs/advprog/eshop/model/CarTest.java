package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    Car car;

    @BeforeEach
    void SetUp(){
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Porsche 911");
        this.car.setCarColor("Silver");
        this.car.setCarQuantity(100);
    }
    @Test
    void testGetCarId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.car.getCarId());
    }

    @Test
    void testGetCarName(){
        assertEquals("Porsche 911", this.car.getCarName());
    }

    @Test
    void testGetCarColor(){
        assertEquals("Silver", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity(){
        assertEquals(100, this.car.getCarQuantity());
    }
}