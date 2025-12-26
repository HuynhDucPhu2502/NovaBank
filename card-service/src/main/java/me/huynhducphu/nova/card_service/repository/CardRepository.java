package me.huynhducphu.nova.card_service.repository;

import me.huynhducphu.nova.card_service.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Admin 12/26/2025
 *
 **/
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByEmail(String email);

    boolean existsByCardNumber(String cardNumber);
}
