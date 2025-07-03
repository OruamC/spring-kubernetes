package com.ocmotta.accounts.controller;

import com.ocmotta.accounts.dto.CustomerDto;
import com.ocmotta.accounts.dto.ErrorResponseDto;
import com.ocmotta.accounts.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CRUD REST APIs for Accounts"
)
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public interface AccountsAPI {

    /**
     * Creates a new account for the customer.
     *
     * @param customerDto the customer details
     * @return ResponseEntity with status 201 Created and a response message
     */
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto);

    /**
     * Fetches account details for a customer based on their mobile number.
     *
     * @param mobileNumber the customer's mobile number
     * @return ResponseEntity with the customer's account details
     */
    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam(name = "mobileNumber")
            @Pattern(regexp = "^$|[0-9]{9}", message = "Mobile number must be 9 digits")
            String mobileNumber);

    /**
     * Updates the account details for a customer.
     *
     * @param customerDto the updated customer details
     * @return ResponseEntity with status 200 OK or 500 Internal Server Error
     */
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto);

    /**
     * Deletes the account for a customer based on their mobile number.
     *
     * @param mobileNumber the customer's mobile number
     * @return ResponseEntity with status 200 OK or 500 Internal Server Error
     */
    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam(name = "mobileNumber")
            @Pattern(regexp = "^$|[0-9]{9}", message = "Mobile number must be 9 digits")
            String mobileNumber);
}
