package me.huynhducphu.nova.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin 2/1/2026
 *
 **/
@RestController
public class GlobalFallbackController {

    @GetMapping("/fallback/global-error")
    public ResponseEntity<@NonNull String> globalError() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Hệ thống đang bận, vui lòng thử lại sau.");
    }

}
