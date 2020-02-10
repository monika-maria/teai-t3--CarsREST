package pl.monikamaria.car.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author monikamaria
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarHateoas extends ResourceSupport { //HATEOAS

    private long carId;
    private String mark;
    private String model;
    private ColorHateoas color;
}
