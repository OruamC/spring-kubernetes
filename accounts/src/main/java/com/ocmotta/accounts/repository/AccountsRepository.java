package com.ocmotta.accounts.repository;

import com.ocmotta.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    /**
     * Finds an account by the associated customer ID.
     *
     * @param customerId the ID of the customer
     * @return an Optional containing the Accounts if found, or empty if not found
     */
    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     * Deletes an account by the associated customer ID.
     *
     * @param customerId the ID of the customer
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
