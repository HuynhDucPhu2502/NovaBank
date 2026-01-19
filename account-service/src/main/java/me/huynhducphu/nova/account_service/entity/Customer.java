package me.huynhducphu.nova.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.base.BaseEntity;

/**
 * Admin 12/26/2025
 *
 **/
@Entity
@Table(
        name = "customers",
        indexes = {
                @Index(name = "idx_customer_email", columnList = "email")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Long customerId;

    String name;

    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "mobile_number", nullable = false)
    String mobileNumber;
}
