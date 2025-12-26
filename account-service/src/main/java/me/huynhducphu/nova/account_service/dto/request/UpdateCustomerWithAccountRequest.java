package me.huynhducphu.nova.account_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.AccountType;

/**
 * Admin 12/26/2025
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCustomerWithAccountRequest {

    @Schema(description = "Email khách hàng", example = "HuynhDucPhu2502@gmail.com")
    @NotEmpty(message = "Email không được rỗng")
    @Email(message = "Email không hợp lệ")
    String email;

    @Schema(description = "Tên khách hàng", example = "Huỳnh Đức Phú")
    @NotEmpty(message = "Tên không được rỗng")
    @Size(min = 5, max = 30, message = "Tên khách hàng phải từ 5 đến 30 ký tự")
    String name;

    @NotNull(message = "Loại tài khoản không được để trống")
    @Schema(description = "Loại tài khoản", example = "SAVINGS")
    AccountType accountType;

    @NotBlank(message = "Địa chỉ chi nhánh không được để trống")
    @Schema(description = "Địa chỉ chi nhánh", example = "120 Xóm Chiếu")
    String branchAddress;
}
