package com.alammar.routers;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().port(9102).host("0.0.0.0");
        rest().post("/pay")
                .param().type(RestParamType.query).name("type").required(true).endParam()
                .param().type(RestParamType.header).name("id").required(true).endParam()
                .route()
                .saga()
                .propagation(SagaPropagation.MANDATORY)
                .option("id", header("id"))
                .compensation("direct:cancelPayment")
                .log("Paying ${header.type} for order #${header.id}")
                .choice()
                .when(x -> Math.random() >= 0.85)
                .throwException(new RuntimeException("Random failure during payment"))
                .end();

        from("direct:cancelPayment")
                .log("Payment #${header.id} has been cancelled");
    }
}
