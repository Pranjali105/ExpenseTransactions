package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.ExpenseTrasactionsRecordsRepository;

@Service
public class ExpenseTrasactionsRecordsServiceImpl implements ExpenseTrasactionsRecordsService {

	@Autowired
	ExpenseTrasactionsRecordsRepository expenseTrasactionsRecordsRepository;

	List<ExpenseTrasactionsRecordsDTO> expenseTrasactionsRecordsLst = new ArrayList<ExpenseTrasactionsRecordsDTO>();

	List<TotalExpenseTrasactionsRecordsDTO> totalExpenseTrasactionsRecordsLst = new ArrayList<TotalExpenseTrasactionsRecordsDTO>();

	@Override
	public ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> getExpenseTrasactionsRecords() {

		expenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository.getExpenseTrasactionsRecords();
		if (expenseTrasactionsRecordsLst == null || expenseTrasactionsRecordsLst.isEmpty()) {
			throw new NullPointerException("Expense transaction records list is null or empty.");
		}
		return ResponseEntity.ok(expenseTrasactionsRecordsLst);

	}

	@Override
	public ResponseEntity<String> addExpenseTrasactionsRecords(
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {

		int n = expenseTrasactionsRecordsRepository.addExpenseTrasactionsRecords(expenseTrasactionsRecordsDTO.getDate(),
				expenseTrasactionsRecordsDTO.getExpense(), expenseTrasactionsRecordsDTO.getType(),
				expenseTrasactionsRecordsDTO.getAmount(), expenseTrasactionsRecordsDTO.getPayment_mode(),
				expenseTrasactionsRecordsDTO.getPayment_mode_type(), expenseTrasactionsRecordsDTO.getBy_whom());

		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");
	}

	@Override
	public ResponseEntity<String> updateExpenseTrasactionsRecords(int id,
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {

		int n = expenseTrasactionsRecordsRepository.updateExpenseTrasactionsRecords(id,
				expenseTrasactionsRecordsDTO.getDate(), expenseTrasactionsRecordsDTO.getExpense(),
				expenseTrasactionsRecordsDTO.getType(), expenseTrasactionsRecordsDTO.getAmount(),
				expenseTrasactionsRecordsDTO.getPayment_mode(), expenseTrasactionsRecordsDTO.getPayment_mode_type(),
				expenseTrasactionsRecordsDTO.getBy_whom());

		if (n == 0) {
			return ResponseEntity.ok("Error occured while updating the data");
		} else
			return ResponseEntity.ok("Data updated successfully");
	}

	@Override
	public ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> exportExpenseTrasactionsRecords() {

		expenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository.getExportExpenseTrasactionsRecords();
		if (expenseTrasactionsRecordsLst == null || expenseTrasactionsRecordsLst.isEmpty()) {
			throw new NullPointerException("Expense transaction records list is null or empty.");
		}
		return ResponseEntity.ok(expenseTrasactionsRecordsLst);
	}
	
}
