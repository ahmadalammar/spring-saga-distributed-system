package com.alammar.routers;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().port(9100).host("localhost");
        from("timer:clock?period=5s")
                .saga()
                .setHeader("id", header(Exchange.TIMER_COUNTER))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .log("Executing saga #${header.id}")
                .to("http://localhost:9101/flight/buy");

    }
}
