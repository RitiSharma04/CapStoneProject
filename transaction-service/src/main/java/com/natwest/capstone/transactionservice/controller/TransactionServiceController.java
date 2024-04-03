package com.natwest.capstone.transactionservice.controller;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.exception.HistoryNotFoundException;
import com.natwest.capstone.transactionservice.exception.InsufficientBalanceException;
import com.natwest.capstone.transactionservice.exception.InvalidAmountException;
import com.natwest.capstone.transactionservice.model.Transaction;
import com.natwest.capstone.transactionservice.service.TransactionServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionService")
public class TransactionServiceController {
    @Autowired
    private TransactionServiceImplement serviceImplement;
    //http://localhost:9094/api/transactionService/withdraw/1/100
    @GetMapping("/withdraw/{accountHolderId}/{amount}")
    public ResponseEntity<Transaction> withdraw(@PathVariable int accountHolderId, @PathVariable double amount) throws InsufficientBalanceException, AccountDoesNotExistException {
        return new ResponseEntity<>(serviceImplement.withdraw(accountHolderId,amount),HttpStatus.OK) ;
    }
    //http://localhost:9094/api/transactionService/deposit/1/100
    @PostMapping("/deposit/{accountHolderId}/{amount}")
    public ResponseEntity<Transaction> deposit(@PathVariable int accountHolderId, @PathVariable double amount) throws InvalidAmountException, AccountDoesNotExistException {
        return new ResponseEntity<>(serviceImplement.deposit(accountHolderId,amount), HttpStatus.OK);
    }
    //http://localhost:9094/api/transactionService/fundTransfer/1/1/100
    @PostMapping("/fundTransfer/{sourceAccountId}/{targetAccountId}/{amount}")
    public ResponseEntity<Transaction> fundTransfer(@PathVariable int sourceAccountId, @PathVariable int targetAccountId, @PathVariable double amount) throws InvalidAmountException, InsufficientBalanceException, AccountDoesNotExistException {
        return new ResponseEntity<>(serviceImplement.fundTransfer(sourceAccountId,targetAccountId,amount), HttpStatus.OK);
    }
    //http://localhost:9094/api/transactionService/payBills/1/100
    @PostMapping("/bills/{accountHolderId}/{amount}")
    public ResponseEntity<Transaction> payBills(@PathVariable int accountHolderId, @PathVariable double amount) throws InvalidAmountException, InsufficientBalanceException, AccountDoesNotExistException {
        return new ResponseEntity<>(serviceImplement.payBills(accountHolderId,amount), HttpStatus.OK);
    }
    //http://localhost:9094/api/transactionService/getHistory/1
    @GetMapping("/history/{id}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable int id) throws HistoryNotFoundException {
        return new ResponseEntity<>(serviceImplement.getHistory(id), HttpStatus.OK);
    }



}
