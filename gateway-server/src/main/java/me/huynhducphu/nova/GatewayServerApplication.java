package me.huynhducphu.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/novabank/account-service/**")
                        .filters(f -> f.rewritePath("/novabank/account-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p
                        .path("/novabank/loan-service/**")
                        .filters(f -> f.rewritePath("/novabank/loan-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOAN-SERVICE"))
                .route(p -> p
                        .path("/novabank/card-service/**")
                        .filters(f -> f.rewritePath("/novabank/card-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARD-SERVICE"))
                .build();
    }

}