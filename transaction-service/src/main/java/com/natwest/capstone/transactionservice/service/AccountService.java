package com.natwest.capstone.transactionservice.service;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.model.Account;
import com.natwest.capstone.transactionservice.repository.AccountRepo;
import com.natwest.capstone.transactionservice.utility.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class AccountService extends TransactionServiceImplement{
    @Autowired
    private AccountRepo accountRepo ;

    public Account createAccount(Account account)
    {
        return accountRepo.save(account) ;
    }
    public Account findAccountById(int id) throws AccountDoesNotExistException {
       Optional<Account> account = accountRepo.findById(id);
       if(account.isPresent()) return account.get();
       throw new AccountDoesNotExistException(AppConstant.ACCOUNT_NOT_FOUND);
    }

}
