package com.example.Account.ServiceTest;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Account.Dto.EventRequestDTO;
import com.example.Account.Entity.Account;
import com.example.Account.Repository.AccountRepository;
import com.example.Account.Repository.AccountTransactionRepository;
import com.example.Account.Service.AccountService;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


@Mock
private AccountRepository accountRepository;

@Mock
private AccountTransactionRepository transactionRepository;

@InjectMocks
private AccountService accountService;

private EventRequestDTO request;

@BeforeEach
void setup() {

    request = new EventRequestDTO();

    request.setEventId("evt-001");
    request.setAccountId("acc-001");
    request.setType("CREDIT");
    request.setAmount(
            BigDecimal.valueOf(100));

    request.setCurrency("USD");

    request.setEventTimestamp(
            Instant.now());
}

@Test
void applyCreditTransaction() {

    when(transactionRepository
            .existsByEventId("evt-001"))
            .thenReturn(false);

    Account account =
            new Account();

    account.setAccountId("acc-001");
    account.setBalance(
            BigDecimal.ZERO);

    when(accountRepository
            .findById("acc-001"))
            .thenReturn(
                    Optional.of(account));

    when(accountRepository
            .save(any(Account.class)))
            .thenReturn(account);

    assertDoesNotThrow(() ->
            accountService.applyTransaction(
                    "acc-001",
                    request));
}

@Test
void duplicateTransactionIgnored() {

    when(transactionRepository
            .existsByEventId("evt-001"))
            .thenReturn(true);

    assertDoesNotThrow(() ->
            accountService.applyTransaction(
                    "acc-001",
                    request));
}

@Test
void getBalanceSuccess() {

    Account account =
            new Account();

    account.setAccountId("acc-001");

    account.setBalance(
            BigDecimal.valueOf(500));

    when(accountRepository
            .findById("acc-001"))
            .thenReturn(
                    Optional.of(account));

    BigDecimal balance =
            accountService.getBalance(
                    "acc-001");

    assertEquals(
            BigDecimal.valueOf(500),
            balance);
}

@Test
void getAccountSuccess() {

    Account account =
            new Account();

    account.setAccountId("acc-001");

    account.setBalance(
            BigDecimal.valueOf(250));

    when(accountRepository
            .findById("acc-001"))
            .thenReturn(
                    Optional.of(account));

    Account result =
            accountService.getAccount(
                    "acc-001");

    assertEquals("acc-001",result.getAccountId());
}

}

