package me.huynhducphu.nova.loan_service.repository;

import me.huynhducphu.nova.loan_service.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Admin 12/26/2025
 *
 **/
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByEmail(String email);

    boolean existsByLoanNumber(String loanNumber);
}
