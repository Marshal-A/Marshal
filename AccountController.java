package com.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // Get account details by account number
    @GetMapping("/number/{accountNumber}")
    public Account getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    // Get account details by account ID
    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    // Update account details
    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        // Ensure the account exists and then update it
        Account existingAccount = accountService.getAccountById(accountId);
        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setUser(account.getUser()); // Assuming user is provided in the request body
        return accountService.updateAccount(existingAccount);
    }
}
