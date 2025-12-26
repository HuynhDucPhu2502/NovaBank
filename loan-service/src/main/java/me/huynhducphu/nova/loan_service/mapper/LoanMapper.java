package me.huynhducphu.nova.loan_service.mapper;

import me.huynhducphu.nova.loan_service.dto.request.CreateLoanRequest;
import me.huynhducphu.nova.loan_service.dto.response.DefaultLoanResponse;
import me.huynhducphu.nova.loan_service.entity.Loan;
import org.mapstruct.Mapper;

/**
 * Admin 12/26/2025
 *
 **/
@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toLoan(CreateLoanRequest createLoanRequest);

    DefaultLoanResponse toDefaultLoanResponse(Loan loan);
}
