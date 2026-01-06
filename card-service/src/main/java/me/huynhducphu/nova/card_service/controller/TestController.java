package me.huynhducphu.nova.card_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.card_service.config.common.ContactInfoConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin 1/5/2026
 *
 **/
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class TestController {

    ContactInfoConfig contactInfoConfig;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(contactInfoConfig.getMessage());
    }


}
