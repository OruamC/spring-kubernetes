package com.ocmotta.accounts.service.impl;

import com.ocmotta.accounts.dto.AccountsDto;
import com.ocmotta.accounts.dto.CustomerDto;
import com.ocmotta.accounts.entity.Accounts;
import com.ocmotta.accounts.entity.Customer;
import com.ocmotta.accounts.exception.CustomerAlreadyExistsException;
import com.ocmotta.accounts.exception.ResourceNotFoundException;
import com.ocmotta.accounts.mapper.AccountsMapper;
import com.ocmotta.accounts.mapper.CustomerMapper;
import com.ocmotta.accounts.repository.AccountsRepository;
import com.ocmotta.accounts.repository.CustomerRepository;
import com.ocmotta.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import static com.ocmotta.accounts.constants.AccountsConstants.ADDRESS;
import static com.ocmotta.accounts.constants.AccountsConstants.SAVINGS;

@Service
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public AccountsServiceImpl(
            final AccountsRepository accountsRepository,
            final CustomerRepository customerRepository
    ) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        var customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        checkRegisteredCustomerWithMobilePhone(customerDto);
        var savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Fetches the account details for a customer based on their mobile number.
     *
     * @param mobileNumber - The customer's mobile number
     * @return CustomerDto containing the account details
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        final var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        "mobileNumber",
                        mobileNumber
                ));

        final var account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account",
                        "customerId",
                        customer.getCustomerId().toString()
                ));
        final var accountsDto = AccountsMapper.mapToAccountsDto(account);
        return CustomerMapper.mapToCustomerDto(customer, accountsDto);
    }

    /**
     * Updates the account details for a customer.
     *
     * @param customerDto - CustomerDto Object containing updated details
     * @return boolean indicating success or failure of the update operation
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.accounts();
        if (accountsDto != null) {
            var accounts = accountsRepository.findById(accountsDto.accountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Account",
                            "accountNumber",
                            accountsDto.accountNumber().toString()
                    ));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            var customerId = accounts.getCustomerId();
            final var customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Customer",
                            "customerId",
                            customerId.toString()
                    ));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Deletes the account for a customer based on their mobile number.
     *
     * @param mobileNumber - The customer's mobile number
     * @return boolean indicating success or failure of the deletion operation
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        final var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        "mobileNumber",
                        mobileNumber
                ));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * Checks if a customer is already registered with the given mobile number.
     *
     * @param customerDto - CustomerDto Object
     * @throws CustomerAlreadyExistsException if a customer with the given mobile number already exists
     */
    private void checkRegisteredCustomerWithMobilePhone(CustomerDto customerDto) {
        customerRepository.findByMobileNumber(customerDto.mobileNumber())
                .ifPresent(c -> {
                    throw new CustomerAlreadyExistsException("Customer already exists registered with given mobileNumber " + customerDto.mobileNumber());
                });
    }

    /**
     * Creates a new account for the given customer.
     *
     * @param customer - Customer object
     * @return Accounts - New account object details
     */
    private Accounts createNewAccount(Customer customer) {
        var newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100_000_000L + new Random().nextInt(900_000_000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);
        return newAccount;
    }
}
