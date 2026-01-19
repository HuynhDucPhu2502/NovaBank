package me.huynhducphu.nova.account_service.service;

import me.huynhducphu.nova.account_service.dto.request.CreateCustomerRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;

/**
 * Admin 1/19/2026
 *
 **/
public interface CustomerService {
    DefaultCustomerResponse createCustomer(
            CreateCustomerRequest request
    );

    void toggleCustomer(String email, Boolean enabled);

    DefaultCustomerResponse getCustomerDetails(String email);
}
