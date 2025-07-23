package com.example.expense_transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.service.DashboardExpenseTrasactionsRecordsService;

@RestController
public class DashboardExpenseTrasactionsRecordsController {

	@Autowired
	DashboardExpenseTrasactionsRecordsService dashboardExpenseTrasactionsRecordsService;

	@PostMapping(value = "/getTotalExpenseTrasactionsRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			@RequestParam(required = false) String expense_category, @RequestParam(required = false) String month,
			@RequestParam(required = true) int year,
			@RequestParam(required = false) String paid_by) {

		ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsService
				.getTotalExpenseTrasactionsRecords(expense_category, month, year, paid_by);

		return dashboardExpenseTrasactionsRecordsLst;
	}

}
