package com.ocmotta.accounts.controller;

import com.ocmotta.accounts.dto.CustomerDto;
import com.ocmotta.accounts.dto.ResponseDto;
import com.ocmotta.accounts.service.IAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.ocmotta.accounts.constants.AccountsConstants.*;

@RestController
public class AccountsController implements AccountsAPI {

    private final IAccountsService accountsService;

    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Override
    public ResponseEntity<ResponseDto> createAccount(CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Override
    public ResponseEntity<CustomerDto> fetchAccountDetails(String mobileNumber) {
        final var customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Override
    public ResponseEntity<ResponseDto> updateAccountDetails(CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(STATUS_500, MESSAGE_500));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAccount(String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(STATUS_500, MESSAGE_500));
        }
    }
}
