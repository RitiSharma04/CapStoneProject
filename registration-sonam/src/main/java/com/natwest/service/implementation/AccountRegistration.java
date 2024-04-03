package com.natwest.service.implementation;

import com.natwest.dto.AccountDto;
import com.natwest.exception.accountregistrationexceptions.AccountAlreadyExistException;
import com.natwest.exception.accountregistrationexceptions.InsufficientBalanceFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidAccountNumberFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidIfcsCodeFoundException;
import com.natwest.model.Account;
import com.natwest.repository.AccountRepository;
import com.natwest.repository.UserRepository;
import com.natwest.service.AccountService;
import com.natwest.utility.AccountAppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRegistration implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    public Account RegisterAccount(Account account)throws AccountAlreadyExistException,InsufficientBalanceFoundException,InvalidAccountNumberFoundException,InvalidIfcsCodeFoundException {
        if(accountRepository.existsById(account.getHolderId())){
            throw new AccountAlreadyExistException(AccountAppConstants.ACCOUNT_ALREADY_EXIST_MESSAGE);
        }
        if (account.getBalance()<=0) {
            throw new InsufficientBalanceFoundException(AccountAppConstants.INSUFFICIENT_BALANCE_MESSAGE);
        }
        if (account.getAccountNumber().length()!=12) {
            throw new InvalidAccountNumberFoundException(AccountAppConstants.INVALID_ACCOUNT_NUMBER_MESSAGE);
        }
        accountRepository.save(account);
        return account;

    }
    @Override
    public AccountDto getAccountDetailsById(int id) {
        Account account=accountRepository.findById(id).get();
        ModelMapper mapper = new ModelMapper();
        AccountDto accountDto = mapper.map(account,AccountDto.class);
        return accountDto;
    }




}
