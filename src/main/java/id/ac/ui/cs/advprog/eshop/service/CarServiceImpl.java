package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car){
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        carIterator.forEachRemaining(carList::add);
        return carList;
    }

    @Override
    public Car findById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public Car edit(String carId, Car updatedCar) {
        return carRepository.update(carId, updatedCar);
    }

    @Override
    public void deleteByCarId(String carId){
        carRepository.delete(carId);
    }
}
