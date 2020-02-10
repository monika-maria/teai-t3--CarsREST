
package pl.monikamaria.car.model;

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
public class Car {

    private long id;
    private String mark;
    private String model;
    private Color color;
    
}
