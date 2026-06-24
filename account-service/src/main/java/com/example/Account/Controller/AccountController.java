package com.example.Account.Controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Account.Dto.EventRequestDTO;
import com.example.Account.Entity.Account;
import com.example.Account.Service.AccountService;



@RestController
@RequestMapping("/accounts")
public class AccountController {


private final AccountService accountService;

public AccountController(
        AccountService accountService) {

    this.accountService = accountService;
}

@PostMapping("/{accountId}/transactions")
public ResponseEntity<String> applyTransaction(

        @PathVariable
        String accountId,

        @RequestBody
        EventRequestDTO request,

        @RequestHeader(
                value = "X-Trace-Id",
                required = false)
        String traceId) {

    accountService.applyTransaction(
            accountId,
            request);

    return ResponseEntity.ok(
            "Transaction Applied Successfully");
}

@GetMapping("/{accountId}")
public ResponseEntity<Account> getAccount(

        @PathVariable
        String accountId) {

    return ResponseEntity.ok(
            accountService.getAccount(
                    accountId));
}

@GetMapping("/{accountId}/balance")
public ResponseEntity<BigDecimal> getBalance(

        @PathVariable
        String accountId) {

    return ResponseEntity.ok(
            accountService.getBalance(
                    accountId));
}

}

