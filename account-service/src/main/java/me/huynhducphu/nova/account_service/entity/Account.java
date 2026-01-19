package me.huynhducphu.nova.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.base.BaseEntity;
import me.huynhducphu.nova.account_service.entity.constants.AccountType;

/**
 * Admin 12/26/2025
 *
 **/
@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    Long accountNumber;

    @Column(name = "customer_id", nullable = false)
    Long customerId;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(name = "branch_address")
    String branchAddress;

}
