package com.example.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
   @Autowired
   private TransactionService transactionService;
   @PostMapping("/deposit/{accountId}")
   public Transaction deposit(@PathVariable Long accountId, @RequestBody Double amount) {
       return transactionService.deposit(accountId, amount);
   }
   @PostMapping("/withdraw/{accountId}")
   public Transaction withdraw(@PathVariable Long accountId, @RequestBody Double amount) {
       return transactionService.withdraw(accountId, amount);
   }
   @PostMapping("/transfer")
   public Transaction transferFunds(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestBody Double amount) {
       return transactionService.transferFunds(fromAccountId, toAccountId, amount);
   }
   @GetMapping("/history/{userId}")
   public List<Transaction> getTransactionHistory(@PathVariable Long userId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
       return transactionService.getTransactionHistory(userId, startDate, endDate);
   }
}