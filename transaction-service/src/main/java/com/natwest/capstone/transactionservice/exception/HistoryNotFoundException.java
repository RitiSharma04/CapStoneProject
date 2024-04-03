package com.natwest.capstone.transactionservice.exception;

public class HistoryNotFoundException extends Exception {
    public HistoryNotFoundException(String historyNotFoundMessage) {
        super(historyNotFoundMessage);
    }
}
