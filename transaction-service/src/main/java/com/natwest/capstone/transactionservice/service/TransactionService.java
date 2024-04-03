package com.natwest.capstone.transactionservice.service;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.exception.HistoryNotFoundException;
import com.natwest.capstone.transactionservice.exception.InsufficientBalanceException;
import com.natwest.capstone.transactionservice.exception.InvalidAmountException;
import com.natwest.capstone.transactionservice.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Transaction withdraw(int accountHolderId, double amount) throws InsufficientBalanceException, AccountDoesNotExistException;
    Transaction deposit(int accountHolderId, double amount) throws InvalidAmountException, AccountDoesNotExistException;
    Transaction fundTransfer(int sourceAccountId , int targetAccountId, double amount) throws InsufficientBalanceException, InvalidAmountException, AccountDoesNotExistException;
    Transaction payBills(int accountHolderId, double amount) throws InvalidAmountException, InsufficientBalanceException, AccountDoesNotExistException;
    List<Transaction> getHistory(int accountHolderId) throws HistoryNotFoundException;
}
