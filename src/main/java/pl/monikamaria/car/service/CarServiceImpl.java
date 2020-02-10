package pl.monikamaria.car.service;

import pl.monikamaria.car.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import pl.monikamaria.car.model.Car;

/**
 * @author monikamaria
 */
@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        this.carList = new ArrayList<>();

        carList.add(new Car(1L, "BMW", "M1", Color.BLACK));
        carList.add(new Car(2L, "Ferrari", "456", Color.WHITE));
        carList.add(new Car(3L, "Renault", "Trafic", Color.SILVER));
        carList.add(new Car(4L, "Tesla", "Model S", Color.BLACK));
    }

    @Override
    public List<Car> getCars() {
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carList.stream().filter(car -> color.equalsIgnoreCase(car.getColor().name())).collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        car.setId(carList.get(carList.size()).getId() + 1);
        return carList.add(car);
    }

    @Override
    public boolean modCar(Car modCar) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == modCar.getId()).findFirst();

        if (foundCar.isPresent()) {
            Car car = foundCar.get();
            car.setMark(modCar.getMark());
            car.setModel(modCar.getModel());
            car.setColor(modCar.getColor());
            return true;
        }
        return false;

    }

    @Override
    public boolean modCarModel(long id, String model) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == id).findFirst();

        if (foundCar.isPresent()) {
            Car car = foundCar.get();
            car.setModel(model);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCarById(long id) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == id).findFirst();

        if (foundCar.isPresent()) {
            carList.remove(id);
            return true;
        }
        return false;
    }

}
