package me.huynhducphu.nova.card_service.config;

import feign.FeignException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Admin 2/1/2026
 *
 **/
@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            FeignException exception = FeignException.errorStatus(methodKey, response);

            if (response.status() >= 400 && response.status() < 500)
                return new RuntimeException("IGNORE_ME");

            return exception;
        };
    }
}
