package me.huynhducphu.nova.loan_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.loan_service.entity.LoanType;

/**
 * Admin 12/26/2025
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLoanRequest {

    @Schema(
            description = "Email khách hàng",
            example = "HuynhDucPhu2502@gmail.com"
    )
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    String email;

    @Schema(
            description = "Loại khoản vay",
            example = "PERSONAL"
    )
    @NotNull(message = "Loại khoản vay không được để trống")
    LoanType loanType;

    @Schema(
            description = "Tổng số tiền vay",
            example = "100000"
    )
    @NotNull(message = "Tổng số tiền vay không được null")
    @Positive(message = "Tổng số tiền vay phải lớn hơn 0")
    Long totalLoan;

}
