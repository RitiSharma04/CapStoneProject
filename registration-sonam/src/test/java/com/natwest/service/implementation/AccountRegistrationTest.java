package com.natwest.service.implementation;

import com.natwest.dto.AccountDto;
import com.natwest.exception.accountregistrationexceptions.AccountAlreadyExistException;
import com.natwest.exception.accountregistrationexceptions.InsufficientBalanceFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidAccountNumberFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidIfcsCodeFoundException;
import com.natwest.model.Account;
import com.natwest.repository.AccountRepository;
import com.natwest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountRegistrationTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountRegistration accountRegistration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterAccount_Positive() throws AccountAlreadyExistException, InsufficientBalanceFoundException, InvalidAccountNumberFoundException, InvalidIfcsCodeFoundException {
        Account account = new Account();
        account.setHolderId(1);
        account.setBalance(1000);
        account.setAccountNumber("123456789012");
        account.setIfscCode("ABCD0123456");

        when(userRepository.existsById(account.getHolderId())).thenReturn(false);

        Account registeredAccount = accountRegistration.RegisterAccount(account);
        assertNotNull(registeredAccount);

    }

    @Test
    void testRegisterAccount_AccountAlreadyExistException() {
        Account account = new Account();
        account.setHolderId(1);

        when(userRepository.existsById(account.getHolderId())).thenReturn(true);

        assertThrows(AccountAlreadyExistException.class, () -> accountRegistration.RegisterAccount(account));
    }

    @Test
    void testRegisterAccount_InsufficientBalanceFoundException() {
        Account account = new Account();
        account.setHolderId(1);
        account.setBalance(-100); // Negative balance

        when(userRepository.existsById(account.getHolderId())).thenReturn(false);

        assertThrows(InsufficientBalanceFoundException.class, () -> accountRegistration.RegisterAccount(account));
    }

    @Test
    void testRegisterAccount_InvalidAccountNumberFoundException() {
        Account account = new Account();
        account.setHolderId(1);
        account.setBalance(1000);
        account.setAccountNumber("123456"); // Invalid account number length

        when(userRepository.existsById(account.getHolderId())).thenReturn(false);

        assertThrows(InvalidAccountNumberFoundException.class, () -> accountRegistration.RegisterAccount(account));
    }

    @Test
    void testRegisterAccount_InvalidIfcsCodeFoundException() {
        Account account = new Account();
        account.setHolderId(1);
        account.setBalance(1000);
        account.setAccountNumber("123456789012");
        account.setIfscCode("INVALIDIFSC"); // Invalid IFSC code format

        when(userRepository.existsById(account.getHolderId())).thenReturn(false);

        assertThrows(InvalidIfcsCodeFoundException.class, () -> accountRegistration.RegisterAccount(account));
    }
    @Test
    void testGetAccountDetailsById() {
        // Create a sample Account
        Account account = new Account();
        account.setId(1);
        account.setHolderId(1001);
        account.setAccountNumber("123456789012");
        account.setBalance(1000.0);
        account.setIfscCode("ABCD0123456");
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        AccountDto resultDto = accountRegistration.getAccountDetailsById(1);

        assertEquals(account.getId(), resultDto.getId());
        assertEquals(account.getHolderId(), resultDto.getHolderId());
        assertEquals(account.getAccountNumber(), resultDto.getAccountNumber());
        assertEquals(account.getIfscCode(), resultDto.getIfscCode());
    }




}