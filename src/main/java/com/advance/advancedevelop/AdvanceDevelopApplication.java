package com.advance.advancedevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.advance"})
@ConfigurationPropertiesScan(value = {"com.advance"})
public class AdvanceDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvanceDevelopApplication.class, args);
    }

}
