package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;

public interface ExpenseTrasactionsRecordsService {

	ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> getExpenseTrasactionsRecords();

	ResponseEntity<String> addExpenseTrasactionsRecords(ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO);

	ResponseEntity<String> updateExpenseTrasactionsRecords(int id,
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO);

	ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> exportExpenseTrasactionsRecords();

}
