package me.huynhducphu.nova.loan_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.loan_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.loan_service.advice.exception.ResourceAlreadyExistsException;
import me.huynhducphu.nova.loan_service.dto.request.CreateLoanRequest;
import me.huynhducphu.nova.loan_service.dto.response.DefaultLoanResponse;
import me.huynhducphu.nova.loan_service.mapper.LoanMapper;
import me.huynhducphu.nova.loan_service.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Admin 12/26/2025
 *
 **/
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoanServiceImpl implements me.huynhducphu.nova.loan_service.service.LoanService {

    // Repository
    LoanRepository loanRepository;

    // Mapper
    LoanMapper loanMapper;

    @Override
    public void createLoan(CreateLoanRequest createLoanRequest) {

        var existedLoan = loanRepository
                .findByEmail(createLoanRequest.getEmail())
                .isPresent();

        if (existedLoan)
            throw new ResourceAlreadyExistsException("Khách hàng đã có khoản vay");

        var loan = loanMapper.toLoan(createLoanRequest);

        loan.setLoanNumber(generateLoanNumber());
        loan.setAmountPaid(0L);
        loan.setOutstandingAmount(createLoanRequest.getTotalLoan());

        loanRepository.save(loan);
    }

    @Override
    public DefaultLoanResponse getLoan(String email) {

        var loan = loanRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Khách hàng không tồn tại"));

        return loanMapper.toDefaultLoanResponse(loan);
    }

    @Override
    public void deleteLoan(String email) {
        var loan = loanRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Thẻ không tồn tại"));

        loanRepository.delete(loan);
    }

    private String generateLoanNumber() {
        String loanNumber;

        do {
            long number = 1_000_000_000_00L + new Random().nextLong(9_000_000_000_00L);
            loanNumber = Long.toString(number);
        } while (loanRepository.existsByLoanNumber(loanNumber));

        return loanNumber;
    }

}
