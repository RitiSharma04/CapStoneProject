package com.natwest.capstone.transactionservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "holder_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int holderId;
    @Column(name = "holder_name",nullable = false)
    private String name;
    @Column(name = "account_number",nullable = false,unique = true)
    private String accountNumber;
    @Column(name = "account_type",nullable = false)
    private String accountType;
    @Column(name = "IFSC_Code",nullable = false)
    private String ifscCode;
    @Column(name = "branch",nullable = false)
    private String branch;
    @Column(name = "account_balance",nullable = false)
    private double balance;
    @JsonBackReference
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    private List<Transaction> history = new ArrayList<>();

    public Account() {
    }

    public Account(int holderId, String name, String accountNumber, String accountType, String ifscCode, String branch, double balance) {
        this.holderId = holderId;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.ifscCode = ifscCode;
        this.branch = branch;
        this.balance = balance;
    }

    public void addTransactionToHistory(Transaction transaction) {
        history.add(transaction);
    }

    public int getHolderId() {
        return holderId;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public String getBranch() {
        return branch;
    }

    public double getBalance() {
        return balance;
    }

    public void setHolderId(int holderId) {
        this.holderId = holderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
