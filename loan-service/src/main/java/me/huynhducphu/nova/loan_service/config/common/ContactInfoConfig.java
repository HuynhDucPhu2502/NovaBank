package me.huynhducphu.nova.loan_service.config.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Map;

/**
 * Admin 1/5/2026
 *
 **/
@RefreshScope
@ConfigurationProperties(prefix = "loan")
@Data
public class ContactInfoConfig {

    private String message;
    private Map<String, String> contactDetails;

}
