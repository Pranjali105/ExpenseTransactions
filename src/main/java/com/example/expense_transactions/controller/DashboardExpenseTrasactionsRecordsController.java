package com.example.expense_transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.service.DashboardExpenseTrasactionsRecordsService;

@RestController
public class DashboardExpenseTrasactionsRecordsController {

	@Autowired
	DashboardExpenseTrasactionsRecordsService dashboardExpenseTrasactionsRecordsService;

	@PostMapping(value = "/getTotalExpenseTrasactionsRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			@RequestBody TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> dashboardExpenseTrasactionsRecordsLst = null;
		
		Integer year = totalExpenseTrasactionsRecordsDTO.getYear();
		
		if (year != null) {
			dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsService
					.getTotalExpenseTrasactionsRecords(totalExpenseTrasactionsRecordsDTO);
		}
		return dashboardExpenseTrasactionsRecordsLst;
	}

}
