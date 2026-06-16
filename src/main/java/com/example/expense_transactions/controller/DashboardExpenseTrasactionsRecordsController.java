package com.example.expense_transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.DashboardExpenseCategoryTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.service.DashboardExpenseTrasactionsRecordsService;

@RestController
public class DashboardExpenseTrasactionsRecordsController {

	@Autowired
	DashboardExpenseTrasactionsRecordsService dashboardExpenseTrasactionsRecordsService;
	ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> dashboardExpenseTrasactionsRecordsLst = null;
	
	ResponseEntity<DashboardExpenseCategoryTrasactionsRecordsDTO> dashboardExpenseCategoryTrasactionsRecordsDTO = null;
	
	ResponseEntity<List<DashboardExpenseCategoryTrasactionsRecordsDTO>> dashboardExpenseCategoryTrasactionsRecordsLst = null;

	DashboardExpenseTrasactionsRecordsController(DashboardExpenseTrasactionsRecordsService dashboardExpenseTrasactionsRecordsService) {
		this.dashboardExpenseTrasactionsRecordsService = dashboardExpenseTrasactionsRecordsService;
	}

	@PostMapping(value = "/getTotalExpenseTrasactionsRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			@RequestBody TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		
		Integer year = totalExpenseTrasactionsRecordsDTO.getYear();
		
		if (year != null) {
			dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsService
					.getTotalExpenseTrasactionsRecords(totalExpenseTrasactionsRecordsDTO);
		}
		return dashboardExpenseTrasactionsRecordsLst;
	}
	
	@PostMapping(value = "/getTotalExpenseCategoryTrasactionsRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DashboardExpenseCategoryTrasactionsRecordsDTO> getTotalExpenseCategoryTrasactionsRecords(
			@RequestBody TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		

		Integer year = totalExpenseTrasactionsRecordsDTO.getYear();

		if (year != null) {
			dashboardExpenseCategoryTrasactionsRecordsDTO = dashboardExpenseTrasactionsRecordsService
					.getTotalExpenseCategoryTrasactionsRecords(totalExpenseTrasactionsRecordsDTO);
		}
		return dashboardExpenseCategoryTrasactionsRecordsDTO;
	}
	
	@PostMapping(value = "/getTotalExpenseTrasactionsRecordsPaidBy", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DashboardExpenseCategoryTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecordsPaidBy(
			@RequestBody TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		

		Integer year = totalExpenseTrasactionsRecordsDTO.getYear();

		if (year != null) {
			dashboardExpenseCategoryTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsService
					.getTotalExpenseTrasactionsRecordsPaidBy(totalExpenseTrasactionsRecordsDTO);
		}
		return dashboardExpenseCategoryTrasactionsRecordsLst;
	}

}
