package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @InjectMocks
    CarController carController;

    @Mock
    CarServiceImpl carService;

    @BeforeEach
    void SetUp(){

    }

    @Test
    void testCreateCarPage() {
        Model model = mock(Model.class);
        String returnedValue = carController.createCarPage(model);
        assertEquals("CreateCar", returnedValue);
    }

    @Test
    void testCreateCarPost() {
        Model model = mock(Model.class);
        Car car = new Car();
        String returnedValue = carController.createCarPost(car, model);

        verify(carService).create(car);
        assertEquals("redirect:listCar", returnedValue);
    }

    @Test
    void testCarListPage() {
        Model model = mock(Model.class);
        String returnedValue = carController.carListPage(model);

        verify(carService).findAll();
        assertEquals("CarList", returnedValue);
    }

    @Test
    void testEditCarPage() {
        Model model = mock(Model.class);
        String returnedValue = carController.editCarPage("carId", model);

        verify(carService).findById("carId");
        assertEquals("EditCar", returnedValue);
    }

    @Test
    void testEditCarPost() {
        Model model = mock(Model.class);
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        String returnedValue = carController.editCarPost(car, model);

        verify(carService).update("eb558e9f-1c39-460e-8860-71af6af63bd6", car);
        assertEquals("redirect:listCar", returnedValue);
    }

    @Test
    void testDeleteCarPost() {
        Car car = new Car();
        String returnedValue = carController.deleteCar("carId");

        assertEquals("redirect:listCar", returnedValue);
    }
}