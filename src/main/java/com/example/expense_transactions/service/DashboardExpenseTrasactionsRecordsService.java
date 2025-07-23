package com.example.expense_transactions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;

public interface DashboardExpenseTrasactionsRecordsService {

	ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			String expense_category, String month, int year, String paid_by);

}
