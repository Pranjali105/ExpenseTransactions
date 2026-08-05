package com.example.expense_transactions.service;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.CategorySubCategoryDTO;
import com.example.expense_transactions.dto.LoanDetailsDTO;

public interface MasterTableService {

	ResponseEntity<String> addCategoryAndSubCategory(CategorySubCategoryDTO categorySubCategoryDTO);

	ResponseEntity<String> addAccountDetails(AccountDetailsDTO accountDetailsDTO);

	ResponseEntity<String> addLoanDetails(LoanDetailsDTO loanDetailsDTO);

}
