package com.ocmotta.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AccountsDto(

        @NotEmpty(message = "AccountNumber cannot be a null or empty")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
        Long accountNumber,

        @NotEmpty(message = "AccountType cannot be null or empty")
        String accountType,

        @NotEmpty(message = "BranchAddress cannot be null or empty")
        String branchAddress
) {
}
