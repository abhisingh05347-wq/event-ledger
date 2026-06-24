package com.example.Account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Account.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
