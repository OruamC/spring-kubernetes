package com.ocmotta.accounts.mapper;

import com.ocmotta.accounts.dto.AccountsDto;
import com.ocmotta.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return new AccountsDto(
                accounts.getAccountNumber(),
                accounts.getAccountType(),
                accounts.getBranchAddress()
        );
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.accountNumber());
        accounts.setAccountType(accountsDto.accountType());
        accounts.setBranchAddress(accountsDto.branchAddress());
        return accounts;
    }
}
