package me.huynhducphu.nova.account_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.dto.base.ApiResponse;
import me.huynhducphu.nova.account_service.dto.request.CreateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.request.UpdateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;
import me.huynhducphu.nova.account_service.service.CustomerAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 12/26/2025
 *
 **/
@Tag(name = "Customer Account API")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/customers")
public class CustomerAccountController {

    CustomerAccountService customerAccountService;

    @PostMapping
    @Operation(summary = "Create customer with account")
    public ResponseEntity<Void> createCustomerWithAccount(
            @RequestBody @Valid CreateCustomerWithAccountRequest request
    ) {
        customerAccountService.createCustomerWithAccount(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    @Operation(summary = "Get customer with account by email")
    public ResponseEntity<ApiResponse<DefaultCustomerResponse>> getCustomerWithAccount(
            @RequestParam String email
    ) {
        var data = customerAccountService.getCustomerDetails(email);
        var response = ApiResponse
                .<DefaultCustomerResponse>builder()
                .result(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update customer with account by email")
    public ResponseEntity<Void> updateCustomerWithAccount(
            @RequestBody @Valid UpdateCustomerWithAccountRequest request
    ) {
        customerAccountService.updateCustomerWithAccount(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete customer with account by email")
    public ResponseEntity<Void> deleteCustomerWithAccount(
            @PathVariable String email
    ) {
        customerAccountService.deleteCustomerWithAccount(email);

        return ResponseEntity.ok().build();
    }


}
