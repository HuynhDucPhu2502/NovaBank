package me.huynhducphu.nova.account_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import me.huynhducphu.nova.account_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.account_service.dto.request.CreateAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultAccountResponse;

import me.huynhducphu.nova.account_service.dto.response.DefaultCardResponse;
import me.huynhducphu.nova.account_service.dto.response.DefaultLoanResponse;
import me.huynhducphu.nova.account_service.entity.Account;
import me.huynhducphu.nova.account_service.mapper.AccountMapper;
import me.huynhducphu.nova.account_service.repository.AccountRepository;
import me.huynhducphu.nova.account_service.repository.CustomerRepository;
import me.huynhducphu.nova.account_service.service.AccountService;
import me.huynhducphu.nova.account_service.service.client.CardClient;
import me.huynhducphu.nova.account_service.service.client.LoanClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Admin 1/19/2026
 *
 **/
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    AccountMapper accountMapper;
    CardClient cardClient;
    LoanClient loanClient;

    @Override
    public DefaultAccountResponse createAccount(CreateAccountRequest request) {
        var customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy khách hàng"));

        var account = Account.builder()
                .customerId(customer.getCustomerId())
                .accountType(request.getAccountType())
                .branchAddress(request.getBranchAddress())
                .build();

        var savedAccount = accountRepository.save(account);

        return accountMapper.toDefaultAccountResponse(savedAccount);
    }

    @Override
    public void toggleAccount(Long accountNumber, boolean enabled) {
        var account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy tài khoản"));

        account.setActive(enabled);
        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public DefaultAccountResponse getAccountDetails(Long accountNumber) {
        var account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy tài khoản số: " + accountNumber));

        var customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy thông tin khách hàng sở hữu tài khoản này"));

        var response = accountMapper.toDefaultAccountResponse(account);

        response.setCard(getCardInfoSafe(customer.getEmail()));
        response.setLoan(getLoanInfoSafe(customer.getEmail()));

        return response;
    }

    // ========================================================================
    // PRIVATE HELPER METHODS (An toàn, chống Null, chống sập luồng chính)
    // ========================================================================

    private DefaultCardResponse getCardInfoSafe(String email) {
        try {
            var apiResponse = cardClient.getCard(email);
            if (apiResponse != null && apiResponse.getResult() != null) {
                return apiResponse.getResult();
            }
        } catch (Exception e) {
            log.warn("Lỗi khi lấy thông tin thẻ (Card Service) cho email {}: {}", email, e.getMessage());
        }
        return null;
    }

    private DefaultLoanResponse getLoanInfoSafe(String email) {
        try {
            var apiResponse = loanClient.getLoan(email);
            if (apiResponse != null && apiResponse.getResult() != null) {
                return apiResponse.getResult();
            }
        } catch (Exception e) {
            log.warn("Lỗi khi lấy thông tin khoản vay (Loan Service) cho email {}: {}", email, e.getMessage());
        }
        return null;
    }
}