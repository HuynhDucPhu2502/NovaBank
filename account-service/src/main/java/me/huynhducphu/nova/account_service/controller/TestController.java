package me.huynhducphu.nova.account_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.service.client.CardClient;
import me.huynhducphu.nova.account_service.service.client.LoanClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin 1/15/2026
 *
 **/
@Tag(name = "Test API")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/test")
public class TestController {

    CardClient cardClient;
    LoanClient loanClient;

    @GetMapping("/get-card-info")
    public ResponseEntity<?> testGetCard(@RequestParam String email) {
        return cardClient.getCard(email);
    }

    @GetMapping("/get-loan-info")
    public ResponseEntity<?> testGetLoan(@RequestParam String email) {
        return loanClient.getCard(email);
    }

}
