package me.huynhducphu.nova.card_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.card_service.dto.base.ApiResponse;
import me.huynhducphu.nova.card_service.dto.request.CreateCardRequest;
import me.huynhducphu.nova.card_service.dto.response.DefaultCardResponse;
import me.huynhducphu.nova.card_service.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 12/26/2025
 *
 **/
@Tag(name = "Card API")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/cards")
public class CardController {

    CardService cardService;

    @PostMapping
    @Operation(summary = "Create card")
    public ResponseEntity<Void> createCard(
            @RequestBody @Valid CreateCardRequest request
    ) {
        cardService.createCard(request);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    @Operation(summary = "Get card information")
    public ResponseEntity<ApiResponse<DefaultCardResponse>> getCard(
            @RequestParam String email
    ) {
        var data = cardService.getCard(email);
        var response = ApiResponse
                .<DefaultCardResponse>builder()
                .result(data)
                .build();

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "delete card")
    public ResponseEntity<Void> deleteCard(
            @PathVariable String email
    ) {
        cardService.deleteCard(email);

        return ResponseEntity
                .ok()
                .build();
    }


}
