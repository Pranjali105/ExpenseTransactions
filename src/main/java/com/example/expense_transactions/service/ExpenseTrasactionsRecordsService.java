package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;

public interface ExpenseTrasactionsRecordsService {

	ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> getExpenseTrasactionsRecords();

	ResponseEntity<String> addExpenseTrasactionsRecords(ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO);

	ResponseEntity<String> updateExpenseTrasactionsRecords(int id,
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO);

	ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> exportExpenseTrasactionsRecords();

	ResponseEntity<List<TotalExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(String expense_category,
			String month, int year, String expense_sub_category, String paid_by);

	ResponseEntity<String> addCategoryAndSubCategory(String category, String subCategory);

}
