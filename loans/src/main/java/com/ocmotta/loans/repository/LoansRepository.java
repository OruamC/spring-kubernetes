package com.ocmotta.loans.repository;

import com.ocmotta.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {

    /**
     * Finds a loan by the mobile number associated with it.
     *
     * @param mobileNumber the mobile number to search for
     * @return an Optional containing the Loans entity if found, or empty if not found
     */
    Optional<Loans> findByMobileNumber(String mobileNumber);

    /**
     * Finds a loan by the loan number.
     *
     * @param loanNumber the loan number to search for
     * @return an Optional containing the Loans entity if found, or empty if not found
     */
    Optional<Loans> findByLoanNumber(String loanNumber);

}