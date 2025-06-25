package com.ocmotta.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a detailed message.
     *
     * @param resourceName the name of the resource that was not found
     * @param fieldName    the name of the field that was used to search for the resource
     * @param fieldValue   the value of the field that was used to search for the resource
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(
                String.format("%s not found with the given input data [%s: '%s']",
                        resourceName, fieldName, fieldValue
                ));
    }
}