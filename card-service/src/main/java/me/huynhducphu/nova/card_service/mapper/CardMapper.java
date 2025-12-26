package me.huynhducphu.nova.card_service.mapper;

import me.huynhducphu.nova.card_service.dto.request.CreateCardRequest;
import me.huynhducphu.nova.card_service.dto.response.DefaultCardResponse;
import me.huynhducphu.nova.card_service.entity.Card;
import org.mapstruct.Mapper;

/**
 * Admin 12/26/2025
 *
 **/
@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toCard(CreateCardRequest createCardRequest);

    DefaultCardResponse toDefaultCardResponse(Card card);

}
