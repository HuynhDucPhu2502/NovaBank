package me.huynhducphu.nova.account_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Admin 12/26/2025
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultCustomerResponse {

    String name;
    String email;
    String mobileNumber;

    DefaultAccountResponse account;
    DefaultCardResponse card;
    DefaultLoanResponse loan;


}


