package me.huynhducphu.nova.account_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.account_service.entity.constants.CardType;

/**
 * Admin 12/26/2025
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultCardResponse {

    String email;
    String cardNumber;

    CardType cardType;

    Long totalLimit;
    Long amountUsed;
    Long availableAmount;

}
