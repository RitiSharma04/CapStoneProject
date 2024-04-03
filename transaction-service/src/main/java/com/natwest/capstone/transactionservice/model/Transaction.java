package com.natwest.capstone.transactionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id")
    private int transactionId ;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_status")
    private String transactionStatus;
    @Column(name = "account_type")
    private String accountType ;
    @Column(name = "transaction_amount")
    private double amount ;
    @Column(name = "Date & Time")
    private LocalDateTime dateTime ;
    @ManyToOne(fetch = FetchType.EAGER)       // this FetchType.Eager is part of loading concept which means data loading should be fast
    @JoinColumn(name = "account")
    private Account account = new Account();

    public Transaction() {
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
