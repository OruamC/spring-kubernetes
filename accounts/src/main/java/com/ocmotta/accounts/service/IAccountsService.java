package com.ocmotta.accounts.service;

import com.ocmotta.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details for a customer based on their mobile number.
     *
     * @param mobileNumber - The customer's mobile number
     * @return CustomerDto containing the account details
     */
    CustomerDto fetchAccount(String mobileNumber);
}
