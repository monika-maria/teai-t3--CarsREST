package pl.monikamaria.car.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author monikamaria
 */
@Configuration
@EnableSwagger2 //odblokowanie możliwości korzystania ze swaggera
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) //jakie API podlegają dokumentacji - wszystkie
                .paths(PathSelectors.any()) //jakie ścieżki podlegają dokumentacji - wszystkie 
                .build();
    }

}
