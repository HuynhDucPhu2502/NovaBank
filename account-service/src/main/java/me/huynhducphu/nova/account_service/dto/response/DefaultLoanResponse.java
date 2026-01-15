package me.huynhducphu.nova.account_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.constants.LoanType;

/**
 * Admin 12/26/2025
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultLoanResponse {

    String email;

    String loanNumber;
    LoanType loanType;

    Long totalLoan;
    Long amountPaid;
    Long outstandingAmount;

}
