package pl.monikamaria.car.hateoas.service;

import pl.monikamaria.car.hateoas.model.CarHateoas;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author monikamaria
 */
public interface CarHateoasService {
    
    List<CarHateoas> getCars();
    
    Optional<CarHateoas> getCarById(long id);
    
    List<CarHateoas> getCarsByColor(String color);
    
}
