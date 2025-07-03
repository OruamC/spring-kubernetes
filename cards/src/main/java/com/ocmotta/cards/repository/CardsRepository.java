package com.ocmotta.cards.repository;

import com.ocmotta.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    /**
     * Finds a card by the mobile number associated with it.
     *
     * @param mobileNumber - The mobile number of the customer
     * @return Optional containing the Cards entity if found, otherwise empty
     */
    Optional<Cards> findByMobileNumber(String mobileNumber);

    /**
     * Finds a card by its card number.
     *
     * @param cardNumber - The card number to search for
     * @return Optional containing the Cards entity if found, otherwise empty
     */
    Optional<Cards> findByCardNumber(String cardNumber);
}
