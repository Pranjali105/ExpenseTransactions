package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;

public interface DashboardExpenseTrasactionsRecordsService {

	ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO);
}
