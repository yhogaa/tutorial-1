package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @InjectMocks
    CarServiceImpl carService;

    @Mock
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

        Mockito.when(carRepository.create(car)).thenReturn(car);
        carService.create(car);

        Mockito.when(carRepository.findAll()).thenReturn(List.of(car).iterator());
        List<Car> carList = carService.findAll();

        assertFalse(carList.isEmpty());
        Car savedCar = carList.getFirst();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Car> carList = new ArrayList<>();
        Mockito.when(carRepository.findAll()).thenReturn(carList.iterator());

        List<Car> cars = carService.findAll();

        assertTrue(cars.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Porsche 911");
        car1.setCarColor("Silver");
        car1.setCarQuantity(100);

        Mockito.when(carRepository.create(car1)).thenReturn(car1);
        carService.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Toyota Supra");
        car2.setCarColor("Black");
        car2.setCarQuantity(20);
        Mockito.when(carRepository.create(car2)).thenReturn(car2);
        carService.create(car2);

        Mockito.when(carRepository.findAll()).thenReturn(List.of(car1, car2).iterator());
        List<Car> carList = carService.findAll();

        assertFalse(carList.isEmpty());
        Car savedCar = carList.removeFirst();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carList.removeFirst();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertTrue(carList.isEmpty());
    }

    @Test
    void testEditCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);

        Mockito.when(carRepository.create(car)).thenReturn(car);
        carService.create(car);

        Car editedCar = new Car();
        editedCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedCar.setCarName("Toyota Supra");
        editedCar.setCarColor("Black");
        editedCar.setCarQuantity(0);
        carService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", editedCar);

        Mockito.when(carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(editedCar);
        Car resultCar = carService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(editedCar, resultCar);
        Mockito.verify(carRepository).update("eb558e9f-1c39-460e-8860-71af6af63bd6", editedCar);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Porsche 911");
        car.setCarColor("Silver");
        car.setCarQuantity(100);

        Mockito.when(carRepository.create(car)).thenReturn(car);
        carService.create(car);

        carService.deleteCarById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Mockito.verify(carRepository).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}