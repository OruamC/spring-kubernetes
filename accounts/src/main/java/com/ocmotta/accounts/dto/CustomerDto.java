package com.ocmotta.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public record CustomerDto(
        @Schema(
                description = "Name of the customer", example = "Eazy Bytes"
        )
        @NotEmpty(message = "Name cannot be null or empty")
        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        String name,

        @Schema(
                description = "Email address of the customer", example = "email@email.com"
        )
        @NotEmpty(message = "Email address cannot be null or empty")
        @Email(message = "Email address must be valid value")
        String email,

        @Schema(
                description = "Mobile number of the customer", example = "987654321"
        )
        @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 9 digits")
        String mobileNumber,

        @Schema(
                description = "Account of the customer"
        )
        AccountsDto accounts) {
}
