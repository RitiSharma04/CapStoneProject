package com.natwest.service;

import com.natwest.dto.AccountDto;
import com.natwest.model.Account;

public interface AccountService {
    public Account RegisterAccount(Account account);
    public AccountDto getAccountDetailsById(int id);
}
