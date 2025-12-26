package me.huynhducphu.nova.card_service.entity;

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
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cardId;

    String email;

    String cardNumber;
    @Enumerated(EnumType.STRING)
    CardType cardType;

    Long totalLimit;
    Long amountUsed;
    Long availableAmount;

}
