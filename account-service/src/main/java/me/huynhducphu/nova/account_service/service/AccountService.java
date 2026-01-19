package me.huynhducphu.nova.account_service.service;

import me.huynhducphu.nova.account_service.dto.request.CreateAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultAccountResponse;

/**
 * Admin 1/19/2026
 *
 **/
public interface AccountService {
    DefaultAccountResponse createAccount(
            CreateAccountRequest request
    );

    void toggleAccount(Long accountNumber, boolean enabled);

    DefaultAccountResponse getAccountDetails(Long accountNumber);
}
