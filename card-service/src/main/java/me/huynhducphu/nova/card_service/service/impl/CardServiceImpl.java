package me.huynhducphu.nova.card_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.huynhducphu.nova.card_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.card_service.advice.exception.ResourceAlreadyExistsException;
import me.huynhducphu.nova.card_service.dto.request.CreateCardRequest;
import me.huynhducphu.nova.card_service.dto.response.DefaultCardResponse;
import me.huynhducphu.nova.card_service.entity.constants.CardType;
import me.huynhducphu.nova.card_service.mapper.CardMapper;
import me.huynhducphu.nova.card_service.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Admin 12/26/2025
 *
 **/
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CardServiceImpl implements me.huynhducphu.nova.card_service.service.CardService {

    // Repository
    CardRepository cardRepository;

    // Mapper
    CardMapper cardMapper;

    @Override
    public void createCard(CreateCardRequest request) {

        var existedCard = cardRepository
                .findByEmail(request.getEmail())
                .isPresent();

        if (existedCard)
            throw new ResourceAlreadyExistsException("Thẻ đã tồn tại");

        var card = cardMapper.toCard(request);

        card.setCardNumber(generateCardNumber());
        card.setTotalLimit(resolveTotalLimit(request.getCardType()));
        card.setAmountUsed(0L);
        card.setAvailableAmount(card.getTotalLimit());

        cardRepository.save(card);
    }

    @Override
    public DefaultCardResponse getCard(String email) {

        var card = cardRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Thẻ không tồn tại"));

        return cardMapper.toDefaultCardResponse(card);
    }

    @Override
    public void deleteCard(String email) {
        var card = cardRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomEntityNotFoundException("Thẻ không tồn tại"));

        cardRepository.delete(card);
    }


    private long resolveTotalLimit(CardType cardType) {
        return switch (cardType) {
            case DEBIT -> 50_000_000L;
            case CREDIT -> 100_000_000L;
            case PREPAID -> 20_000_000L;
        };
    }

    private String generateCardNumber() {
        String cardNumber;

        do {
            long number = 1_000_000_000_00L + new Random().nextLong(9_000_000_000_00L);
            cardNumber = Long.toString(number);
        } while (cardRepository.existsByCardNumber(cardNumber));

        return cardNumber;
    }


}
