package com.example.springsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.springsse"})
public class SpringSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSseApplication.class, args);
    }

}
