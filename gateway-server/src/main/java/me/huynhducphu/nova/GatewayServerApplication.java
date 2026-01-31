package me.huynhducphu.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

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
                        .filters(f -> f
                                .rewritePath("/novabank/account-service/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p
                        .path("/novabank/loan-service/**")
                        .filters(f -> f
                                .rewritePath("/novabank/loan-service/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOAN-SERVICE"))
                .route(p -> p
                        .path("/novabank/card-service/**")
                        .filters(f -> f
                                .rewritePath("/novabank/card-service/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARD-SERVICE")).build();
    }


//    @Bean
//    public RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(1, 1, 1);
//    }


}
