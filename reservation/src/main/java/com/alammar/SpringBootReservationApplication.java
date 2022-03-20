package com.alammar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Camel Application
 */
@SpringBootApplication
public class SpringBootReservationApplication {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootReservationApplication.class, args);
    }
}

