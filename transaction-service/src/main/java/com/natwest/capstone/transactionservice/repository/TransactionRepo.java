package com.natwest.capstone.transactionservice.repository;

import com.natwest.capstone.transactionservice.model.Account;
import com.natwest.capstone.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
