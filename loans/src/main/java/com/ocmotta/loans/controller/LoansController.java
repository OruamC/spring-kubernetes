package com.ocmotta.loans.controller;

import com.ocmotta.loans.dto.LoansDto;
import com.ocmotta.loans.dto.ResponseDto;
import com.ocmotta.loans.service.ILoansService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.ocmotta.loans.constants.LoansConstants.*;

@RestController
public class LoansController implements LoansAPI {

    private final ILoansService iLoansService;

    public LoansController(final ILoansService iLoansService) {
        this.iLoansService = iLoansService;
    }

    @Override
    public ResponseEntity<ResponseDto> createLoan(String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber) {
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Override
    public ResponseEntity<ResponseDto> updateLoanDetails(LoansDto loansDto) {
        boolean isUpdated = iLoansService.updateLoan(loansDto);
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
    public ResponseEntity<ResponseDto> deleteLoanDetails(String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
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
