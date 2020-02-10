package pl.monikamaria.car.restcontroller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.monikamaria.car.model.Car;
import pl.monikamaria.car.service.CarService;

/**
 *
 * @author monikamaria
 */
@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CarRestController {

    private CarService carSrv;

    @Autowired
    public CarRestController(CarService carSrv) {
        this.carSrv = carSrv;
    }

    @ApiOperation(value = "Get all cars")
    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carSrv.getCars(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get car by id")
    @ApiImplicitParam(name = "id", value = "Car ID", required = true, dataType = "long", paramType = "path")
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> foundCar = carSrv.getCarById(id);
        return foundCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get car by color")
    @ApiImplicitParam(name = "color", value = "car color", required = true, dataType = "String", paramType = "path")
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color){
        List<Car> carList = carSrv.getCarsByColor(color);

        if(!carList.isEmpty()){
            return new ResponseEntity<>(carList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Add new car")
    @ApiImplicitParam(name = "Car", value = "Car model", required = true, dataType = "Car", paramType = "body")
    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        if(carSrv.addCar(car)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Modify the car")
    @ApiImplicitParam(name = "Car", value = "Car model", required = true, dataType = "Car", paramType = "body")
    @PutMapping
    public ResponseEntity modCar(@RequestBody Car car){
        if(carSrv.modCar(car)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else if(!carSrv.modCar(car)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Modify the car's model")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "car ID", required = true, dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "model", value = "car model", required = true, dataType = "String", paramType = "path")
    })
    @PatchMapping("/{id}/model/{model}")
    public ResponseEntity modCarModel(@PathVariable long id, @PathVariable String model){
        if(carSrv.modCarModel(id, model)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if(!carSrv.modCarModel(id, model)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Remove the car by id")
    @ApiImplicitParam(name = "id", value = "car ID", required = true, dataType = "long", paramType = "path")
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> removeCarById(@PathVariable long id) {
        if(carSrv.removeCarById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (!carSrv.removeCarById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
