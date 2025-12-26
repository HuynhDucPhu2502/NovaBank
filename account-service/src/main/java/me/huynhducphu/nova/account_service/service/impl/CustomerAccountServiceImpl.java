package me.huynhducphu.nova.account_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.account_service.advice.exception.ResourceAlreadyExistsException;
import me.huynhducphu.nova.account_service.dto.request.CreateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.request.UpdateCustomerWithAccountRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;
import me.huynhducphu.nova.account_service.entity.Account;
import me.huynhducphu.nova.account_service.entity.Customer;
import me.huynhducphu.nova.account_service.mapper.AccountMapper;
import me.huynhducphu.nova.account_service.mapper.CustomerMapper;
import me.huynhducphu.nova.account_service.repository.AccountRepository;
import me.huynhducphu.nova.account_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Admin 12/26/2025
 *
 **/
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerAccountServiceImpl implements me.huynhducphu.nova.account_service.service.CustomerAccountService {

    // Repository
    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    // Mapper
    AccountMapper accountMapper;
    CustomerMapper customerMapper;

    @Override
    public void createCustomerWithAccount(
            CreateCustomerWithAccountRequest request
    ) {

        var existedCustomer = customerRepository
                .findByEmail(request.getEmail())
                .isPresent();

        if (existedCustomer)
            throw new ResourceAlreadyExistsException("Khách hàng đã tồn tại");

        Customer customer = customerMapper.toCustomer(request);
        Customer savedCustomer = customerRepository.save(customer);

        Account account = accountMapper.toAccount(request);
        account.setCustomerId(savedCustomer.getCustomerId());

        accountRepository.save(account);
    }

    @Override
    public DefaultCustomerResponse getCustomerWithAccount(String email) {

        var existedCustomer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Khách hàng không tồn tại"));

        var existedAccount = accountRepository
                .findByCustomerId(existedCustomer.getCustomerId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Tài khoản không tồn tại"));

        var defaultCustomerResponse = customerMapper.toDefaultCustomerResponse(existedCustomer);
        var defaultAccountResponse = accountMapper.toDefaultAccountResponse(existedAccount);

        defaultCustomerResponse.setAccount(defaultAccountResponse);

        return defaultCustomerResponse;
    }

    @Override
    public void updateCustomerWithAccount(
            UpdateCustomerWithAccountRequest request
    ) {
        var existedCustomer = customerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomEntityNotFoundException("Khách hàng không tồn tại"));

        var existedAccount = accountRepository
                .findByCustomerId(existedCustomer.getCustomerId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Tài khoản không tồn tại"));

        existedCustomer.setName(request.getName());
        existedAccount.setAccountType(request.getAccountType());
        existedAccount.setBranchAddress(request.getBranchAddress());
    }

    @Override
    public void deleteCustomerWithAccount(
            String email
    ) {
        var existedCustomer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Khách hàng không tồn tại"));

        var existedAccount = accountRepository
                .findByCustomerId(existedCustomer.getCustomerId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Tài khoản không tồn tại"));

        accountRepository.delete(existedAccount);
        customerRepository.delete(existedCustomer);
    }


}
