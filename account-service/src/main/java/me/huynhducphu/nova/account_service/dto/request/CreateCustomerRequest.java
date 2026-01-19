package me.huynhducphu.nova.account_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Admin 1/19/2026
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerRequest {

    @Schema(description = "Tên khách hàng", example = "Huỳnh Đức Phú")
    @NotEmpty(message = "Tên không được rỗng")
    @Size(min = 5, max = 30, message = "Tên khách hàng phải từ 5 đến 30 ký tự")
    String name;

    @Schema(description = "Email khách hàng", example = "HuynhDucPhu2502@gmail.com")
    @NotEmpty(message = "Email không được rỗng")
    @Email(message = "Email không hợp lệ")
    String email;

    @Schema(description = "Số điện thoại khách hàng", example = "0845125203")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "Số điện thoại phải có 10 ký số")
    String mobileNumber;

}
