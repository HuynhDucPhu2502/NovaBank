package me.huynhducphu.nova.account_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.dto.base.ApiResponse;
import me.huynhducphu.nova.account_service.dto.request.CreateCustomerRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;
import me.huynhducphu.nova.account_service.service.CustomerService;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 1/19/2026
 *
 **/
@Tag(name = "Customer API", description = "Quản lý thông tin khách hàng")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/customers")
public class CustomerController {

    CustomerService customerService;

    @Operation(summary = "Tạo mới khách hàng", description = "Tạo khách hàng mới với thông tin cơ bản")
    @PostMapping
    public ApiResponse<DefaultCustomerResponse> createCustomer(
            @RequestBody @Valid CreateCustomerRequest request
    ) {
        var result = customerService.createCustomer(request);

        return ApiResponse.<DefaultCustomerResponse>builder()
                .result(result)
                .message("Tạo khách hàng thành công")
                .build();
    }

    @Operation(summary = "Lấy thông tin khách hàng", description = "Tìm kiếm khách hàng theo Email")
    @GetMapping("/{email}")
    public ApiResponse<DefaultCustomerResponse> getCustomerDetails(
            @PathVariable("email") String email
    ) {
        var result = customerService.getCustomerDetails(email);

        return ApiResponse.<DefaultCustomerResponse>builder()
                .result(result)
                .message("Lấy thông tin khách hàng thành công")
                .build();
    }

    @Operation(summary = "Cập nhật trạng thái", description = "Kích hoạt hoặc vô hiệu hóa khách hàng")
    @PatchMapping("/{email}/toggle")
    public ApiResponse<Void> toggleCustomer(
            @PathVariable("email") String email,
            @RequestParam("enabled") boolean enabled
    ) {
        customerService.toggleCustomer(email, enabled);

        return ApiResponse.<Void>builder()
                .message(enabled ? "Đã kích hoạt khách hàng" : "Đã vô hiệu hóa khách hàng")
                .build();
    }
}