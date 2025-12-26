package me.huynhducphu.nova.card_service.service;

import me.huynhducphu.nova.card_service.dto.request.CreateCardRequest;
import me.huynhducphu.nova.card_service.dto.response.DefaultCardResponse;

/**
 * Admin 12/26/2025
 *
 **/
public interface CardService {
    void createCard(CreateCardRequest request);

    DefaultCardResponse getCard(String email);

    void deleteCard(String email);
}
