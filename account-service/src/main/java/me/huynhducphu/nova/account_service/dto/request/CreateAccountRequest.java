package me.huynhducphu.nova.account_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.constants.AccountType;

/**
 * Admin 1/19/2026
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountRequest {

    @Schema(description = "Email khách hàng", example = "HuynhDucPhu2502@gmail.com")
    @NotEmpty(message = "Email không được rỗng")
    @Email(message = "Email không hợp lệ")
    String email;

    @NotNull(message = "Loại tài khoản không được để trống")
    @Schema(description = "Loại tài khoản", example = "SAVINGS")
    AccountType accountType;

    @NotBlank(message = "Địa chỉ chi nhánh không được để trống")
    @Schema(description = "Địa chỉ chi nhánh", example = "120 Xóm Chiếu")
    String branchAddress;

}
