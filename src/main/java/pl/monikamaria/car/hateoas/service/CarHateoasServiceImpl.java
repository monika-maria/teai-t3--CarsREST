package pl.monikamaria.car.hateoas.service;

import org.springframework.stereotype.Service;
import pl.monikamaria.car.hateoas.model.CarHateoas;
import pl.monikamaria.car.hateoas.model.ColorHateoas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author monikamaria
 */
@Service
public class CarHateoasServiceImpl implements CarHateoasService {

    private List<CarHateoas> carList;

    public CarHateoasServiceImpl() {
        this.carList = new ArrayList<>();

        carList.add(new CarHateoas(1, "BMW", "M1", ColorHateoas.BLACK));
        carList.add(new CarHateoas(2, "Ferrari", "456", ColorHateoas.WHITE));
        carList.add(new CarHateoas(3, "Renault", "Trafic", ColorHateoas.SILVER));
        carList.add(new CarHateoas(4, "Tesla", "Model S", ColorHateoas.BLACK));
    }

    @Override
    public List<CarHateoas> getCars() {
        return carList;
    }

    @Override
    public Optional<CarHateoas> getCarById(long id) {
        return carList.stream().filter(car -> car.getCarId() == id).findFirst();
    }

    @Override
    public List<CarHateoas> getCarsByColor(String color) {
        return carList.stream().filter(car -> color.equalsIgnoreCase(car.getColor().name())).collect(Collectors.toList());
    }

}
