package com.leoh.bloomit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BloomitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloomitApplication.class, args);
    }

}
