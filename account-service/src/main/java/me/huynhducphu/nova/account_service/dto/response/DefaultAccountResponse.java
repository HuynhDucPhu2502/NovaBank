package me.huynhducphu.nova.account_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.constants.AccountType;

/**
 * Admin 12/26/2025
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultAccountResponse {

    AccountType accountType;
    String branchAddress;

    DefaultCardResponse card;
    DefaultLoanResponse loan;

}
