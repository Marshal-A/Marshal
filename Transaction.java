package com.example.transaction;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.catalina.User;
@Entity
@Table(name="Transaction")
public class Transaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private Double amount;
   private String transactionType; // DEPOSIT, WITHDRAW, TRANSFER
   private LocalDate transactionDate;
   @ManyToOne
   private User user;
   @ManyToOne
   private Account fromAccount; // Account from which the transfer/withdraw happens
   @ManyToOne
   private Account toAccount; // If transfer, the receiving account
   // Getters and Setters
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public LocalDate getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(LocalDate transactionDate) {
	this.transactionDate = transactionDate;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Account getFromAccount() {
	return fromAccount;
}
public void setFromAccount(Account fromAccount) {
	this.fromAccount = fromAccount;
}
public Account getToAccount() {
	return toAccount;
}
public void setToAccount(Account toAccount) {
	this.toAccount = toAccount;
}
@Override
public String toString() {
	return "Transaction [id=" + id + ", amount=" + amount + ", transactionType=" + transactionType
			+ ", transactionDate=" + transactionDate + ", user=" + user + ", getId()=" + getId() + ", getAmount()="
			+ getAmount() + ", getTransactionType()=" + getTransactionType() + ", getTransactionDate()="
			+ getTransactionDate() + ", getUser()=" + getUser() + ", getClass()=" + getClass() + ", hashCode()="
			+ hashCode() + ", toString()=" + super.toString() + "]";
}

   
}

