package com.catly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDevApplication.class, args);
    }
}
