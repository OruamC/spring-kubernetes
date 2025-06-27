package com.ocmotta.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotEmpty(message = "Name cannot be null or empty")
        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        String name,

        @NotEmpty(message = "Email address cannot be null or empty")
        @Email(message = "Email address must be valid value")
        String email,

        @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 9 digits")
        String mobileNumber,

        AccountsDto accounts) {
}
