package com.ocmotta.cards.controller;

import com.ocmotta.cards.dto.CardsDto;
import com.ocmotta.cards.dto.ResponseDto;
import com.ocmotta.cards.service.ICardsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.ocmotta.cards.constants.CardsConstants.*;

@RestController
public class CardsController implements CardsAPI {

    private final ICardsService iCardsService;

    public CardsController(final ICardsService iCardsService) {
        this.iCardsService = iCardsService;
    }

    @Override
    public ResponseEntity<ResponseDto> createCard(String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201)
                );
    }

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Override
    public ResponseEntity<ResponseDto> updateCardDetails(CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCardDetails(String mobileNumber) {
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_DELETE));
        }
    }
}
