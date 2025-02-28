package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CarService {
    Car create(Car car);
    List<Car> findAll();
    Car findById(String carId);
    Car edit(String id, Car updatedCar);
    void deleteByCarId(String carId);
}