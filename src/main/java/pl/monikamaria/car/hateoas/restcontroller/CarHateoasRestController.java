package pl.monikamaria.car.hateoas.restcontroller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.monikamaria.car.hateoas.model.CarHateoas;
import pl.monikamaria.car.hateoas.service.CarHateoasService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 * @author monikamaria
 */
@RestController
@RequestMapping(value = "/cars/hateoas", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarHateoasRestController {

    private CarHateoasService carSrv;

    @Autowired
    public CarHateoasRestController(CarHateoasService carSrv) {
        this.carSrv = carSrv;
    }

    @ApiOperation(value = "Get all cars")
    @GetMapping
    public ResponseEntity<Resources<CarHateoas>> getCars() {
        List<CarHateoas> cars = carSrv.getCars();
        cars.forEach(car -> car.add(linkTo(CarHateoasRestController.class).slash(car.getCarId()).withSelfRel()));
        Link link = linkTo(CarHateoasRestController.class).withSelfRel();
        Resources<CarHateoas> carResources = new Resources<>(cars, link);
        return  new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    @ApiOperation(value = "Get car by id")
    @ApiImplicitParam(name = "id", value = "car ID", required = true, dataType = "long", paramType = "path")
    @GetMapping("/{id}")
    public ResponseEntity<Resource<CarHateoas>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarHateoasRestController.class).slash(id).withSelfRel();
        Optional<CarHateoas> foundCar = carSrv.getCarById(id);
        Resource<CarHateoas> carResource = new Resource<>(foundCar.get(), link);
        return new ResponseEntity<>(carResource, HttpStatus.OK);
    }

    @ApiOperation(value = "Get car by color")
    @ApiImplicitParam(name = "color", value = "car color", required = true, dataType = "String", paramType = "path")
    @GetMapping("/color/{color}")
    public ResponseEntity<Resources<CarHateoas>> getCarsByColor(@PathVariable String color){
        List<CarHateoas> cars = carSrv.getCarsByColor(color);

        cars.forEach(car -> car.add(linkTo(CarHateoasRestController.class).slash(car.getId()).withSelfRel()));
        cars.forEach(car -> car.add(linkTo(CarHateoasRestController.class).withRel("allColors")));
        Link link = linkTo(CarHateoasRestController.class).withSelfRel();
        Resources<CarHateoas> carResources = new Resources<>(cars, link);
        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

}
