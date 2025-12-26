package me.huynhducphu.nova.account_service.repository;

import me.huynhducphu.nova.account_service.entity.Account;
import me.huynhducphu.nova.account_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Admin 12/26/2025
 *
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
