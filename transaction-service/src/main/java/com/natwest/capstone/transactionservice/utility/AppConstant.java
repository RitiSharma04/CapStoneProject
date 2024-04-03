package com.natwest.capstone.transactionservice.utility;

public interface AppConstant {
    String TRANSACTION_TYPE_DEBIT = "debit";
    String TRANSACTION_TYPE_CREDIT = "credit";
    String TRANSACTION_SUCCESS = "successful";
    String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient balance in your account ";
    String INVALID_AMOUNT_MESSAGE = "Enter a valid amount to proceed";
    String ACCOUNT_NOT_FOUND = "Sorry,This Account does not exist to make transactions.";
    String HISTORY_NOT_FOUND_MESSAGE = "Sorry, Could not fetch the history related to this account.";
}
