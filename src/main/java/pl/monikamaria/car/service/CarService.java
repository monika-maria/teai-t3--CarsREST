package pl.monikamaria.car.service;

import java.util.List;
import java.util.Optional;
import pl.monikamaria.car.model.Car;

/**
 *
 * @author monikamaria
 */
public interface CarService {
    
    List<Car> getCars();
    
    Optional<Car> getCarById(long id);
    
    List<Car> getCarsByColor(String color);
    
    boolean addCar(Car car);
    
    boolean modCar(Car car);
    
    boolean modCarModel(long id, String model);
    
    boolean removeCarById(long id);
    
}
