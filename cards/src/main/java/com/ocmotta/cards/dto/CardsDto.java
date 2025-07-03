package com.ocmotta.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
public record CardsDto(
        @NotEmpty(message = "Mobile Number can not be a null or empty")
        @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile Number must be 9 digits")
        @Schema(
                description = "Mobile Number of Customer", example = "4354437687"
        )
        String mobileNumber,

        @NotEmpty(message = "Card Number can not be a null or empty")
        @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
        @Schema(
                description = "Card Number of the customer", example = "100646930341"
        )
        String cardNumber,

        @NotEmpty(message = "CardType can not be a null or empty")
        @Schema(
                description = "Type of the card", example = "Credit Card"
        )
        String cardType,

        @Positive(message = "Total card limit should be greater than zero")
        @Schema(
                description = "Total amount limit available against a card", example = "100000"
        )
        int totalLimit,

        @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
        @Schema(
                description = "Total amount used by a Customer", example = "1000"
        )
        int amountUsed,

        @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
        @Schema(
                description = "Total available amount against a card", example = "90000"
        )
        int availableAmount
) {
}
