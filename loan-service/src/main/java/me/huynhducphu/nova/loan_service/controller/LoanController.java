package me.huynhducphu.nova.loan_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.loan_service.dto.base.ApiResponse;
import me.huynhducphu.nova.loan_service.dto.request.CreateLoanRequest;
import me.huynhducphu.nova.loan_service.dto.response.DefaultLoanResponse;
import me.huynhducphu.nova.loan_service.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 12/26/2025
 *
 **/
@Tag(name = "Loan API")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/loans")
public class LoanController {

    LoanService loanService;

    @PostMapping
    @Operation(summary = "Create loan")
    public ResponseEntity<Void> createLoan(
            @RequestBody @Valid CreateLoanRequest request
    ) {
        loanService.createLoan(request);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    @Operation(summary = "Get loan information")
    public ResponseEntity<ApiResponse<DefaultLoanResponse>> getLoan(
            @RequestParam String email
    ) {
        var data = loanService.getLoan(email);
        var response = ApiResponse
                .<DefaultLoanResponse>builder()
                .result(data)
                .build();

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete loan")
    public ResponseEntity<Void> deleteLoan(
            @PathVariable String email
    ) {
        loanService.deleteLoan(email);

        return ResponseEntity
                .ok()
                .build();
    }

}
