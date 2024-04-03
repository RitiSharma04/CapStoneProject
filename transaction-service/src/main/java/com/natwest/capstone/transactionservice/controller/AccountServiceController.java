package com.natwest.capstone.transactionservice.controller;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.model.Account;
import com.natwest.capstone.transactionservice.model.Transaction;
import com.natwest.capstone.transactionservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountServiceController {

    @Autowired
    private AccountService service ;
    //http://localhost:9094/api/accounts/account
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        return new ResponseEntity<>(service.createAccount(account), HttpStatus.OK) ;
    }
    //http://localhost:9094/api/accounts/account/{id}
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> findAccount(@PathVariable int id) throws AccountDoesNotExistException {
        return new ResponseEntity<>(service.findAccountById(id), HttpStatus.OK) ;
    }


}
