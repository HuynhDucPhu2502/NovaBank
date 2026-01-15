package me.huynhducphu.nova.account_service.service;

import me.huynhducphu.nova.account_service.dto.request.CreateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.request.UpdateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;

/**
 * Admin 12/26/2025
 *
 **/
public interface CustomerAccountService {
    void createCustomerWithAccount(
            CreateCustomerWithAccountRequest request
    );

    DefaultCustomerResponse getCustomerDetails(String email);

    void updateCustomerWithAccount(
            UpdateCustomerWithAccountRequest request
    );

    void deleteCustomerWithAccount(
            String email
    );
}
