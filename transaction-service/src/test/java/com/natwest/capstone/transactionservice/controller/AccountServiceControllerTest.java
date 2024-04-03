package com.natwest.capstone.transactionservice.controller;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.model.Account;
import com.natwest.capstone.transactionservice.service.AccountService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountServiceControllerTest {
    @Autowired
    private TestRestTemplate restTemplate ;
    @MockBean
    private AccountService service ;

    @Test
    void createAccount() {
        Account account = new Account(1,"ankit","12345","current","SBIN12","azadpur",1000) ;
        when(service.createAccount(account)).thenReturn(account);
        HttpEntity<Account> request = new HttpEntity<>(account);
        ResponseEntity<Account> result = restTemplate.postForEntity("/accounts/account",request,Account.class);
        assertEquals(account.getHolderId(),result.getBody().getHolderId());
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

    @Test
    void findAccount() throws AccountDoesNotExistException {
        Account account1 = mock(Account.class);
        when(account1.getHolderId()).thenReturn(1);
        when(service.findAccountById(1)).thenReturn(account1);
        ResponseEntity<Account> result = restTemplate.getForEntity("/accounts/account/1", Account.class);
        assertEquals(account1.getHolderId(),result.getBody().getHolderId());
    }
}