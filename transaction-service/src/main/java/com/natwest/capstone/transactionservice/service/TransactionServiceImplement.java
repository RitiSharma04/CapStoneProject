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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImplement implements TransactionService{
    @Autowired
    private TransactionRepo transactionRepo ;
    @Autowired
    private AccountRepo accountRepo ;

    @Override
    public Transaction withdraw(int accountHolderId, double amount) throws InsufficientBalanceException, AccountDoesNotExistException {

        if(!accountRepo.findById(accountHolderId).isPresent()) throw new AccountDoesNotExistException(AppConstant.ACCOUNT_NOT_FOUND) ;
        Account account = accountRepo.findById(accountHolderId).get();
        double bal = account.getBalance();
        if(bal<amount) throw new InsufficientBalanceException(AppConstant.INSUFFICIENT_BALANCE_MESSAGE);

        account.setBalance(bal-amount);

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(AppConstant.TRANSACTION_SUCCESS);
        transaction.setTransactionType(AppConstant.TRANSACTION_TYPE_DEBIT);
        transaction.setAccountType(account.getAccountType());
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());

        account.addTransactionToHistory(transaction);

        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction deposit(int accountHolderId, double amount) throws InvalidAmountException, AccountDoesNotExistException {
        if(!accountRepo.findById(accountHolderId).isPresent()) throw new AccountDoesNotExistException(AppConstant.ACCOUNT_NOT_FOUND) ;
        Account account = accountRepo.findById(accountHolderId).get();

        if(amount<=0) throw new InvalidAmountException(AppConstant.INVALID_AMOUNT_MESSAGE);
        account.setBalance(account.getBalance()+amount);

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(AppConstant.TRANSACTION_SUCCESS);
        transaction.setTransactionType(AppConstant.TRANSACTION_TYPE_CREDIT);
        transaction.setAccountType(account.getAccountType());
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());

        account.addTransactionToHistory(transaction);
        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction fundTransfer(int sourceAccountId, int targetAccountId, double amount) throws InsufficientBalanceException, InvalidAmountException, AccountDoesNotExistException {
        Optional<Account> account1 = accountRepo.findById(sourceAccountId) ;
        Optional<Account> account2 = accountRepo.findById(targetAccountId) ;

        if(!account1.isPresent() || !account2.isPresent()) throw new AccountDoesNotExistException(AppConstant.ACCOUNT_NOT_FOUND) ;

        Account sourceAccount = account1.get();
        Account targetAccount = account2.get();

        if(amount<=0) throw new InvalidAmountException(AppConstant.INVALID_AMOUNT_MESSAGE);

        else if(sourceAccount.getBalance()<amount) throw new InsufficientBalanceException(AppConstant.INSUFFICIENT_BALANCE_MESSAGE);
        sourceAccount.setBalance(sourceAccount.getBalance()-amount);
        targetAccount.setBalance(targetAccount.getBalance()+amount);

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionStatus(AppConstant.TRANSACTION_SUCCESS);
        transaction1.setTransactionType(AppConstant.TRANSACTION_TYPE_DEBIT);
        transaction1.setAccountType(sourceAccount.getAccountType());
        transaction1.setAccount(sourceAccount);
        transaction1.setAmount(amount);
        transaction1.setDateTime(LocalDateTime.now());

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionStatus(AppConstant.TRANSACTION_SUCCESS);
        transaction2.setTransactionType(AppConstant.TRANSACTION_TYPE_DEBIT);
        transaction2.setAccountType(targetAccount.getAccountType());
        transaction2.setAccount(targetAccount);
        transaction2.setAmount(amount);
        transaction2.setDateTime(LocalDateTime.now());

        sourceAccount.addTransactionToHistory(transaction1);
        targetAccount.addTransactionToHistory(transaction2);

        return transactionRepo.save(transaction1);
    }

    @Override
    public Transaction payBills(int accountHolderId, double amount) throws InvalidAmountException, InsufficientBalanceException, AccountDoesNotExistException {
        if(!accountRepo.findById(accountHolderId).isPresent()) throw new AccountDoesNotExistException(AppConstant.ACCOUNT_NOT_FOUND) ;
        Account account = accountRepo.findById(accountHolderId).get();

        if(amount<=0) throw new InvalidAmountException(AppConstant.INVALID_AMOUNT_MESSAGE);
        else if(account.getBalance()<amount) throw new InsufficientBalanceException(AppConstant.INSUFFICIENT_BALANCE_MESSAGE);
        account.setBalance(account.getBalance()-amount);

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(AppConstant.TRANSACTION_SUCCESS);
        transaction.setTransactionType(AppConstant.TRANSACTION_TYPE_DEBIT);
        transaction.setAccountType(account.getAccountType());
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());

        account.addTransactionToHistory(transaction);
        return transactionRepo.save(transaction);    }

    @Override
    public List<Transaction> getHistory(int id) throws HistoryNotFoundException {
        List<Transaction> transactions = transactionRepo.findAll();
        List<Transaction> history = new ArrayList<>();
        if(transactions != null && !transactions.isEmpty()){
            for(Transaction t: transactions){
                if(t.getAccount().getHolderId() == id){
                    history.add(t);
                }
            }
            return history ;
        }
        throw new HistoryNotFoundException(AppConstant.HISTORY_NOT_FOUND_MESSAGE);
    }

}
