package com.saccharine.crudapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CrudApper {

    public static void main(String[] args) {
        SpringApplication.run(CrudApper.class, args);
        System.out.println("Go to http://localhost:9000/");
    }
}