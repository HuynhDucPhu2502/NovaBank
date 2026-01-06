package me.huynhducphu.nova.card_service.config.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Map;

/**
 * Admin 1/5/2026
 *
 **/
@RefreshScope
@ConfigurationProperties(prefix = "card")
@Data
public class ContactInfoConfig {

    private String message;
    private Map<String, String> contactDetails;

}
