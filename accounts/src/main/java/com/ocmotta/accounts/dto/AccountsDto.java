package com.ocmotta.accounts.dto;

public record AccountsDto(
        Long accountNumber,
        String accountType,
        String branchAddress
) {
}
