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

    public AccountsController(final IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Override
    public ResponseEntity<ResponseDto> createAccount(final CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Override
    public ResponseEntity<CustomerDto> fetchAccountDetails(final String mobileNumber) {
        final var customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Override
    public ResponseEntity<ResponseDto> updateAccountDetails(final CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
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
    public ResponseEntity<ResponseDto> deleteAccount(final String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_DELETE));
        }
    }
}
