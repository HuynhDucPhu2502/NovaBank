package me.huynhducphu.nova.account_service.service.client;

import me.huynhducphu.nova.account_service.dto.base.ApiResponse;
import me.huynhducphu.nova.account_service.dto.response.DefaultCardResponse;
import me.huynhducphu.nova.account_service.dto.response.DefaultLoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Admin 1/12/2026
 *
 **/
@FeignClient("LOAN-SERVICE")
public interface LoanClient {

    @GetMapping(value = "/api/loans", consumes = "application/json")
    ResponseEntity<ApiResponse<DefaultLoanResponse>> getCard(@RequestParam String email);
}
