package me.huynhducphu.nova.account_service.mapper;

import me.huynhducphu.nova.account_service.dto.request.CreateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultAccountResponse;
import me.huynhducphu.nova.account_service.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Admin 12/26/2025
 *
 **/
@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    Account toAccount(CreateCustomerWithAccountRequest request);

    DefaultAccountResponse toDefaultAccountResponse(Account account);

}
