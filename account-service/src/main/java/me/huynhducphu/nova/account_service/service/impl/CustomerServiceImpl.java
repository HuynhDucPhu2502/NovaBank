package me.huynhducphu.nova.account_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.account_service.advice.exception.ResourceAlreadyExistsException;
import me.huynhducphu.nova.account_service.dto.request.CreateCustomerRequest;
import me.huynhducphu.nova.account_service.dto.response.DefaultCustomerResponse;
import me.huynhducphu.nova.account_service.entity.Customer;
import me.huynhducphu.nova.account_service.mapper.CustomerMapper;
import me.huynhducphu.nova.account_service.repository.CustomerRepository;
import me.huynhducphu.nova.account_service.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    // Repository
    CustomerRepository customerRepository;

    // Mapper
    CustomerMapper customerMapper;

    @Override
    public DefaultCustomerResponse createCustomer(
            CreateCustomerRequest request
    ) {

        var isExisted = customerRepository
                .findByEmail(request.getEmail())
                .isPresent();

        if (isExisted)
            throw new ResourceAlreadyExistsException("Khách hàng đã tồn tại");

        Customer customer = Customer
                .builder()
                .name(request.getName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .build();

        var savedCustomer = customerRepository.save(customer);

        return customerMapper.toDefaultCustomerResponse(savedCustomer);
    }

    @Override
    public void toggleCustomer(String email, Boolean enabled) {
        var customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy khách hàng"));

        customer.setActive(enabled);
        customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public DefaultCustomerResponse getCustomerDetails(String email) {
        var customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Không tìm thấy khách hàng"));

        return customerMapper.toDefaultCustomerResponse(customer);
    }


}
