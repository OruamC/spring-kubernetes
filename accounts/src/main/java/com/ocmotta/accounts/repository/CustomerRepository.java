package com.ocmotta.accounts.repository;

import com.ocmotta.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return an Optional containing the Customer if found, or empty if not found
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
