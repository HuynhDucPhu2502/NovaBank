package me.huynhducphu.nova.account_service.mapper;

import me.huynhducphu.nova.account_service.dto.request.CreateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;
import me.huynhducphu.nova.account_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Admin 12/26/2025
 *
 **/
@Mapper(componentModel = "spring")
public interface CustomerMapper {


    @Mapping(target = "customerId", ignore = true)
    Customer toCustomer(CreateCustomerWithAccountRequest request);

    DefaultCustomerResponse toDefaultCustomerResponse(Customer customer);

}
