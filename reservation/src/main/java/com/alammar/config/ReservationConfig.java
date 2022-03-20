package com.alammar.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReservationConfig {

    @Bean
    @Autowired
    public CamelContext initCamelContext(List<RouteBuilder> routeBuilderList) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        routeBuilderList.stream().forEach(routeBuilder -> {
            try {
                camelContext.addRoutes(routeBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        camelContext.addService(new InMemorySagaService());
        camelContext.start();
        return camelContext;
    }


}
