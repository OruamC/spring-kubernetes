package com.ocmotta.cards.service.impl;

import com.ocmotta.cards.dto.CardsDto;
import com.ocmotta.cards.entity.Cards;
import com.ocmotta.cards.exception.CardAlreadyExistsException;
import com.ocmotta.cards.exception.ResourceNotFoundException;
import com.ocmotta.cards.mapper.CardsMapper;
import com.ocmotta.cards.repository.CardsRepository;
import com.ocmotta.cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.ocmotta.cards.constants.CardsConstants.CREDIT_CARD;
import static com.ocmotta.cards.constants.CardsConstants.NEW_CARD_LIMIT;

@Service
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository cardsRepository;

    public CardsServiceImpl(final CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        final var optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        optionalCards.ifPresent(
                cards -> {
                    throw new CardAlreadyExistsException("Card already exists for mobile number: " + mobileNumber);
                }
        );
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CREDIT_CARD);
        newCard.setTotalLimit(NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        final var cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card",
                        "mobileNumber",
                        mobileNumber
                )
        );
        return CardsMapper.mapToCardsDto(cards);
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        final var cards = cardsRepository.findByCardNumber(cardsDto.cardNumber()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card",
                        "cardNumber",
                        cardsDto.cardNumber()
                )
        );
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        final var cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card",
                        "mobileNumber",
                        mobileNumber
                )
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
