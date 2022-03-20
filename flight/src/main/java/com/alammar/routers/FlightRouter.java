package com.alammar.routers;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        restConfiguration().port(9101).host("0.0.0.0");
        rest().post("/flight/buy")
                .param().type(RestParamType.header).name("id").required(true).endParam()
                .route()
                .saga()
                .propagation(SagaPropagation.SUPPORTS)
                .option("id", header("id"))
                .compensation("direct:cancelPurchase")
                .log("Buying flight #${header.id}")
                .to("http4://payment:9102/pay?bridgeEndpoint=true&type=flight")
                .log("Payment for flight #${header.id} done");

        from("direct:cancelPurchase")
                .log("Flight purchase #${header.id} has been cancelled");

    }
}
