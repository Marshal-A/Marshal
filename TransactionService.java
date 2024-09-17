package com.example.transaction;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Transaction deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTransactionDate(LocalDate.now());
        transaction.setFromAccount(account);
        transaction.setUser((User)account.getUser()); // Set the user associated with the account

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAW");
        transaction.setTransactionDate(LocalDate.now());
        transaction.setFromAccount(account);
        transaction.setUser((User) account.getUser()); // Set the user associated with the account

        return transactionRepository.save(transaction);
    }

    @Transactional
    public List<Transaction> getTransactionHistory(Long userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserIdAndTransactionDateBetween(userId, startDate, endDate);
    }

    @Transactional
    public Transaction transferFunds(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Recipient account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDate.now());
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setUser((User)fromAccount.getUser()); // Set the user associated with the fromAccount

        return transactionRepository.save(transaction);
    }
}