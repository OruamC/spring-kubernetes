package com.ocmotta.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public record AccountsDto(

        @Schema(
                description = "Account number of the customer", example = "3784658376"
        )
        @NotEmpty(message = "AccountNumber cannot be a null or empty")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
        Long accountNumber,

        @Schema(
                description = "Account type of the customer", example = "Savings"
        )
        @NotEmpty(message = "AccountType cannot be null or empty")
        String accountType,

        @Schema(
                description = "Account branch address", example = "123 Main St, Springfield, USA"
        )
        @NotEmpty(message = "BranchAddress cannot be null or empty")
        String branchAddress
) {
}
