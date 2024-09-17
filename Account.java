package com.example.transaction;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String accountNumber;
   private Double balance;
   @ManyToOne
   private User user;
   @OneToMany(mappedBy = "Account")
   private List<Transaction> transactions;
   // Getters and Setters
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public List<Transaction> getTransactions() {
	return transactions;
}
public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
}
@Override
public String toString() {
	return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + ", user=" + user
			+ ", transactions=" + transactions + ", getId()=" + getId() + ", getAccountNumber()=" + getAccountNumber()
			+ ", getBalance()=" + getBalance() + ", getUser()=" + getUser() + ", getTransactions()=" + getTransactions()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}
   

}
