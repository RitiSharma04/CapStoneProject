package com.natwest.capstone.transactionservice.service;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.exception.HistoryNotFoundException;
import com.natwest.capstone.transactionservice.exception.InsufficientBalanceException;
import com.natwest.capstone.transactionservice.exception.InvalidAmountException;
import com.natwest.capstone.transactionservice.model.Account;
import com.natwest.capstone.transactionservice.model.Transaction;
import com.natwest.capstone.transactionservice.repository.AccountRepo;
import com.natwest.capstone.transactionservice.repository.TransactionRepo;
import com.natwest.capstone.transactionservice.utility.AppConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplementTest {

    @Mock
    private TransactionRepo transactionRepo;
    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private TransactionServiceImplement transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testWithdraw_Successful() throws InsufficientBalanceException, AccountDoesNotExistException {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        double amount = 500;
        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));

        when(transactionRepo.save(any(Transaction.class))).thenAnswer(t->t.getArgument(0));

        Transaction transaction = transactionService.withdraw(account.getHolderId(), amount);

        assertEquals(500,account.getBalance());
        assertEquals(transaction.getTransactionType(), AppConstant.TRANSACTION_TYPE_DEBIT);
        assertEquals(transaction.getTransactionStatus(), AppConstant.TRANSACTION_SUCCESS);
        assertEquals(transaction.getAmount(), amount);
        verify(transactionRepo,times(1)).save(transaction);
    }
    @Test
    void testWithdraw_InsufficientAmount()
    {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));
        double amountToWithdraw = 1500;
        InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class,()->transactionService.withdraw(account.getHolderId(),amountToWithdraw));
        assertTrue(exception.getMsg().equals(AppConstant.INSUFFICIENT_BALANCE_MESSAGE));
        verify(transactionRepo,never()).save(any(Transaction.class));
    }
    @Test
    void testWithdraw_AccountNotExist()
    {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.empty());
        double amountToWithdraw = 500;
        AccountDoesNotExistException exception = assertThrows(AccountDoesNotExistException.class,()->transactionService.withdraw(account.getHolderId(),amountToWithdraw));
        assertTrue(exception.getMessage().equals(AppConstant.ACCOUNT_NOT_FOUND));
        verify(transactionRepo,never()).save(any(Transaction.class));
    }
    @Test
    void testDeposit_Success() throws AccountDoesNotExistException, InvalidAmountException {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        double amount = 500;
        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));

        when(transactionRepo.save(any(Transaction.class))).thenAnswer(t->t.getArgument(0));

        Transaction transaction = transactionService.deposit(account.getHolderId(), amount);

        assertEquals(1500,account.getBalance());
        assertEquals(transaction.getTransactionType(), AppConstant.TRANSACTION_TYPE_CREDIT);
        assertEquals(transaction.getTransactionStatus(), AppConstant.TRANSACTION_SUCCESS);
        assertEquals(transaction.getAmount(), amount);
        verify(transactionRepo,times(1)).save(transaction);
    }
    @Test
    void testDeposit_InvalidAmount()
    {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));
        double amount = -5;
        InvalidAmountException exception = assertThrows(InvalidAmountException.class,()->transactionService.deposit(account.getHolderId(),amount));
        assertTrue(exception.getMessage().equals(AppConstant.INVALID_AMOUNT_MESSAGE));
        verify(transactionRepo,never()).save(any(Transaction.class));
    }
    @Test
    void testDeposit_AccountNotExist()
    {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.empty());
        double amount = 500;
        AccountDoesNotExistException exception = assertThrows(AccountDoesNotExistException.class,()->transactionService.withdraw(account.getHolderId(),amount));
        assertTrue(exception.getMessage().equals(AppConstant.ACCOUNT_NOT_FOUND));
        verify(transactionRepo,never()).save(any(Transaction.class));
    }
    @Test
    void testFundTransfer_Success() throws AccountDoesNotExistException, InsufficientBalanceException, InvalidAmountException {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        Account account2 = new Account();
        account2.setHolderId(124);
        account2.setBalance(6000);

        double amount = 500;
        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));
        when(accountRepo.findById(account2.getHolderId())).thenReturn(Optional.of(account2));

        when(transactionRepo.save(any(Transaction.class))).thenAnswer(t->t.getArgument(0));

        Transaction transaction = transactionService.fundTransfer(account.getHolderId(),account2.getHolderId(),amount);

        assertEquals(500,account.getBalance());
        assertEquals(6500,account2.getBalance());

        assertEquals(transaction.getTransactionType(), AppConstant.TRANSACTION_TYPE_DEBIT);
        assertEquals(transaction.getTransactionStatus(), AppConstant.TRANSACTION_SUCCESS);
        assertEquals(transaction.getAmount(), amount);

        verify(transactionRepo,times(1)).save(transaction);
    }
    @Test
    void testPayBills_Successful() throws InsufficientBalanceException, AccountDoesNotExistException, InvalidAmountException {
        Account account = new Account();
        account.setHolderId(123);
        account.setBalance(1000);

        double amount = 500;
        when(accountRepo.findById(account.getHolderId())).thenReturn(Optional.of(account));

        when(transactionRepo.save(any(Transaction.class))).thenAnswer(t->t.getArgument(0));

        Transaction transaction = transactionService.payBills(account.getHolderId(), amount);

        assertEquals(500,account.getBalance());
        assertEquals(transaction.getTransactionType(), AppConstant.TRANSACTION_TYPE_DEBIT);
        assertEquals(transaction.getTransactionStatus(), AppConstant.TRANSACTION_SUCCESS);
        assertEquals(transaction.getAmount(), amount);
        verify(transactionRepo,times(1)).save(transaction);
    }

    @Test
    void testTransactionHistory() throws HistoryNotFoundException {
        Account account = new Account();
        account.setHolderId(123);

        Transaction transaction = mock(Transaction.class);

        List<Transaction> list = new ArrayList<>();
        list.add(transaction);

        when(transactionRepo.findAll()).thenReturn(list);
        when(transaction.getAccount()).thenReturn(account);
        assertTrue(transactionService.getHistory(123).containsAll(list));

    }
}