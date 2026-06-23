package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.AccountPassbookDTO;
import com.example.expense_transactions.dto.CreditDetailsDTO;

public interface AccountDetailsService {

	ResponseEntity<AccountDetailsDTO> getAccountDetails(String accountNo);

	ResponseEntity<String> addCreditDetails(CreditDetailsDTO creditDetailsDTO);

	ResponseEntity<List<AccountPassbookDTO>> getAllAccountTransactionRecords(AccountDetailsDTO accountDetailsDTO);

	ResponseEntity<List<AccountPassbookDTO>> exportAllAccountTransactionRecords(AccountDetailsDTO accountDetailsDTO);

}
