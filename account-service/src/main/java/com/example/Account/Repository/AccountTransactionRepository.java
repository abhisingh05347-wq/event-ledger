package com.example.Account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Account.Entity.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {

    boolean existsByEventId(String eventId);
}
