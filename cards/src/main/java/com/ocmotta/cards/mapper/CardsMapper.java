package com.ocmotta.cards.mapper;

import com.ocmotta.cards.dto.CardsDto;
import com.ocmotta.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards) {
        return new CardsDto(
                cards.getMobileNumber(),
                cards.getCardNumber(),
                cards.getCardType(),
                cards.getTotalLimit(),
                cards.getAmountUsed(),
                cards.getAvailableAmount()
        );
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.cardNumber());
        cards.setMobileNumber(cardsDto.mobileNumber());
        cards.setCardType(cardsDto.cardType());
        cards.setTotalLimit(cardsDto.totalLimit());
        cards.setAmountUsed(cardsDto.amountUsed());
        cards.setAvailableAmount(cardsDto.availableAmount());
        return cards;
    }
}
