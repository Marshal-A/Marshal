package com.example.transaction;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // Retrieve an account by its account number
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    // Retrieve an account by its ID
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Update the account balance or other details
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}

