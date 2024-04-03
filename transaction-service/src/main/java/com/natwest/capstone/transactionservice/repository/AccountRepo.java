package com.natwest.capstone.transactionservice.repository;

import com.natwest.capstone.transactionservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
}
