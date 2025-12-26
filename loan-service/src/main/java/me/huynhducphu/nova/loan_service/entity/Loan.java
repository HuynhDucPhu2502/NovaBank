package me.huynhducphu.nova.loan_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Admin 12/26/2025
 *
 **/
@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long loanId;

    String email;

    String loanNumber;
    @Enumerated(EnumType.STRING)
    LoanType loanType;

    Long totalLoan;
    Long amountPaid;
    Long outstandingAmount;

}
