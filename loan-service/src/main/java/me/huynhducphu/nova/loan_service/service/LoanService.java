package me.huynhducphu.nova.loan_service.service;

import me.huynhducphu.nova.loan_service.dto.request.CreateLoanRequest;
import me.huynhducphu.nova.loan_service.dto.response.DefaultLoanResponse;

/**
 * Admin 12/26/2025
 *
 **/
public interface LoanService {
    void createLoan(CreateLoanRequest createLoanRequest);

    DefaultLoanResponse getLoan(String email);

    void deleteLoan(String email);
}
