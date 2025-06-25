package com.ocmotta.accounts.mapper;

import com.ocmotta.accounts.dto.CustomerDto;
import com.ocmotta.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }
}
