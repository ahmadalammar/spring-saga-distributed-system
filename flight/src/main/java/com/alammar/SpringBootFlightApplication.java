package com.alammar;

import com.alammar.routers.FlightRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Camel Application
 */
@SpringBootApplication
public class SpringBootFlightApplication {


    @Autowired
    CamelContext camelContext;

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlightApplication.class, args);
    }

}

