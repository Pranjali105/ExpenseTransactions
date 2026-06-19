package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.CreditDetails;

public interface AccountDetailsService {

	ResponseEntity<AccountDetailsDTO> getAccountDetails(String accountNo);

	ResponseEntity<String> addCreditDetails(CreditDetails creditDetails);

}
