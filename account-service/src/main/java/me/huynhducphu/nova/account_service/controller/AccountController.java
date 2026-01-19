package me.huynhducphu.nova.account_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.dto.base.ApiResponse;
import me.huynhducphu.nova.account_service.dto.request.CreateAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultAccountResponse;
import me.huynhducphu.nova.account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 1/19/2026
 *
 **/
@Tag(name = "Account API", description = "Quản lý tài khoản ngân hàng")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/accounts")
public class AccountController {

    AccountService accountService;

    @Operation(summary = "Tạo tài khoản mới", description = "Mở tài khoản ngân hàng cho khách hàng đã tồn tại")
    @PostMapping
    public ApiResponse<DefaultAccountResponse> createAccount(
            @RequestBody @Valid CreateAccountRequest request
    ) {
        var result = accountService.createAccount(request);

        return ApiResponse.<DefaultAccountResponse>builder()
                .result(result)
                .message("Tạo tài khoản thành công")
                .build();
    }

    @Operation(summary = "Lấy thông tin tài khoản", description = "Tra cứu chi tiết tài khoản theo số tài khoản")
    @GetMapping("/{accountNumber}")
    public ApiResponse<DefaultAccountResponse> getAccountDetails(
            @PathVariable("accountNumber") Long accountNumber
    ) {
        var result = accountService.getAccountDetails(accountNumber);

        return ApiResponse.<DefaultAccountResponse>builder()
                .result(result)
                .message("Lấy thông tin tài khoản thành công")
                .build();
    }

    @Operation(summary = "Cập nhật trạng thái", description = "Khóa hoặc mở khóa tài khoản")
    @PatchMapping("/{accountNumber}/toggle")
    public ApiResponse<Void> toggleAccount(
            @PathVariable("accountNumber") Long accountNumber,
            @RequestParam("enabled") boolean enabled
    ) {
        accountService.toggleAccount(accountNumber, enabled);

        return ApiResponse.<Void>builder()
                .message(enabled ? "Đã kích hoạt tài khoản" : "Đã khóa tài khoản")
                .build();
    }
}