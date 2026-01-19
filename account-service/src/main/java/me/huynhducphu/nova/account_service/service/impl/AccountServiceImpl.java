package me.huynhducphu.nova.account_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.dto.request.CreateAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultAccountResponse;
import me.huynhducphu.nova.account_service.entity.Account;
import me.huynhducphu.nova.account_service.mapper.AccountMapper;
import me.huynhducphu.nova.account_service.repository.AccountRepository;
import me.huynhducphu.nova.account_service.repository.CustomerRepository;
import me.huynhducphu.nova.account_service.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Admin 1/19/2026
 *
 **/
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

    // Repository
    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    // Mapper
    AccountMapper accountMapper;


    @Override
    public DefaultAccountResponse createAccount(
            CreateAccountRequest request
    ) {

        var customer = customerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng"));
        
        var account = Account
                .builder()
                .customerId(customer.getCustomerId())
                .accountType(request.getAccountType())
                .branchAddress(request.getBranchAddress())
                .build();

        var savedAccount = accountRepository.save(account);

        return accountMapper.toDefaultAccountResponse(savedAccount);
    }

    @Override
    public void toggleAccount(Long accountNumber, boolean enabled) {
        var account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tài khoản"));

        account.setActive(enabled);
        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public DefaultAccountResponse getAccountDetails(Long accountNumber) {
        var account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tài khoản"));

        return accountMapper.toDefaultAccountResponse(account);
    }


}
