package me.huynhducphu.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()

                // ACCOUNT SERVICE
                .route(p -> p
                        .path("/novabank/account-service/**")
                        .filters(f -> f
                                .rewritePath("/novabank/account-service/(?<segment>.*)", "/${segment}")
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())
                                )
                                .circuitBreaker(c -> c
                                        .setName("accountCB")
                                        .setFallbackUri("forward:/fallback/global-error")
                                )
                        )
                        .uri("lb://ACCOUNT-SERVICE"))

                // LOAN SERVICE
                .route(p -> p
                        .path("/novabank/loan-service/**")
                        .filters(f -> f
                                .rewritePath("/novabank/loan-service/(?<segment>.*)", "/${segment}")
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())
                                )
                                .circuitBreaker(c -> c
                                        .setName("loanCB")
                                        .setFallbackUri("forward:/fallback/global-error")
                                )
                        )
                        .uri("lb://LOAN-SERVICE"))

                // CARD SERVICE
                .route(p -> p
                        .path("/novabank/card-service/**")
                        .filters(f -> f
                                .rewritePath("/novabank/card-service/(?<segment>.*)", "/${segment}")
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())
                                )
                                .circuitBreaker(c -> c
                                        .setName("cardCB")
                                        .setFallbackUri("forward:/fallback/global-error")
                                )
                        )
                        .uri("lb://CARD-SERVICE"))


                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(20, 40);
    }

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono
                .just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress())
                        .getAddress()
                        .getHostAddress()
                );
    }

}