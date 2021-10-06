package com.carshop.admin;

import com.carshop.common.entity.Aandrijving;
import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Model;
import com.carshop.common.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan({"com.carshop.common.entity"})
@EnableSwagger2
@ConfigurationPropertiesScan
public class CarshopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarshopBackEndApplication.class, args);
    }

}
