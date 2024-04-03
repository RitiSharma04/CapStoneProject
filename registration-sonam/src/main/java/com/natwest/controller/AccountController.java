package com.natwest.controller;

import com.natwest.dto.AccountDto;
import com.natwest.model.Account;
import com.natwest.model.User;
import com.natwest.service.AccountService;
import com.natwest.utility.AccountAppConstants;
import com.natwest.utility.UserAppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/account")
    public ResponseEntity<String> registerAccount(@RequestBody Account account) {
        accountService.RegisterAccount(account);
        return new ResponseEntity<>(AccountAppConstants.REGISTRATION_SUCCESSFUL_MESSAGE, HttpStatus.OK);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable int id) {
        AccountDto account = accountService.getAccountDetailsById(id);
        return ResponseEntity.ok(account);
    }

}
