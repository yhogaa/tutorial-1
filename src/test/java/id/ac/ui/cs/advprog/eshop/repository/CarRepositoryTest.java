package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;
    @BeforeEach
    void SetUp(){

    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Porsche 911");
        car1.setCarColor("Silver");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Toyota Supra");
        car2.setCarColor("Black");
        car2.setCarQuantity(20);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCarQuantityIfNegative() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        assertFalse(car.getCarQuantity() < 0);
    }

    @Test
    void testEditCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car editedCar = new Car();
        editedCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedCar.setCarName("Toyota Supra");
        editedCar.setCarColor("Black");
        editedCar.setCarQuantity(0);
        carRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", editedCar);

        car = carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(editedCar.getCarId(), car.getCarId());
        assertEquals(editedCar.getCarName(), car.getCarName());
        assertEquals(editedCar.getCarColor(), car.getCarColor());
        assertEquals(editedCar.getCarQuantity(), car.getCarQuantity());
    }

    @Test
    void testFindCarIfFalseID() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car editedCar = new Car();
        editedCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedCar.setCarName("Toyota Supra");
        editedCar.setCarColor("Black");
        editedCar.setCarQuantity(0);

        car = carRepository.findById("x");
        assertNull(car);
    }

    @Test
    void testEditCarIfIdDoesntExist() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car editedCar = new Car();
        editedCar.setCarId("eb558e9f-1c39");
        editedCar.setCarName("Toyota Supra");
        editedCar.setCarQuantity(-5);
        Car uneditedCar = carRepository.update("eb558e9f-1c39", editedCar);

        assertNull(uneditedCar);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);
        carRepository.create(car);

        carRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertFalse(carRepository.findAll().hasNext());
    }
}