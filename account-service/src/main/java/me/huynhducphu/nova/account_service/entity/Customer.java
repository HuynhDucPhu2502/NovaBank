package me.huynhducphu.nova.account_service.entity;

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
@Table(
        name = "customers",
        indexes = {
                @Index(name = "idx_customer_email", columnList = "email"),
                @Index(name = "idx_customer_mobile", columnList = "mobile_number")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Long customerId;

    String name;

    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "mobile_number", unique = true, nullable = false)
    String mobileNumber;
}
