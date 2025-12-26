package me.huynhducphu.nova.card_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.card_service.entity.CardType;

/**
 * Admin 12/26/2025
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCardRequest {

    @Schema(description = "Email khách hàng", example = "HuynhDucPhu2502@gmail.com")
    @NotEmpty(message = "Email không được rỗng")
    @Email(message = "Email không hợp lệ")
    String email;

    @Schema(
            description = "Loại thẻ",
            example = "DEBIT"
    )
    @NotNull(message = "Loại thẻ không được để trống")
    CardType cardType;
}
