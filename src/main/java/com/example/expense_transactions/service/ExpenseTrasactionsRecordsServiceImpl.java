package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.ExpenseTrasactionsRecordsRepository;

@Service
public class ExpenseTrasactionsRecordsServiceImpl implements ExpenseTrasactionsRecordsService {

	@Autowired
	ExpenseTrasactionsRecordsRepository expenseTrasactionsRecordsRepository;

	List<ExpenseTrasactionsRecordsDTO> expenseTrasactionsRecordsLst = new ArrayList<ExpenseTrasactionsRecordsDTO>();

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

		boolean is12DigitNumber = is12DigitNumber(expenseTrasactionsRecordsDTO.getAccountNo());
		int n = 0;
		
		if(is12DigitNumber == true) {
		
		n = expenseTrasactionsRecordsRepository.addExpenseTrasactionsRecords(expenseTrasactionsRecordsDTO.getDate(),
				expenseTrasactionsRecordsDTO.getExpenseCategory(), expenseTrasactionsRecordsDTO.getExpenseSubCategory(),
				expenseTrasactionsRecordsDTO.getAmount(), expenseTrasactionsRecordsDTO.getPaymentMode(),
				expenseTrasactionsRecordsDTO.getPaymentModeType(), expenseTrasactionsRecordsDTO.getByWhom(), expenseTrasactionsRecordsDTO.getAccountNo());
		}
		else
		{
			return ResponseEntity.ok("Account number must be a 12-digit number.");
		}
		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");
	}

	private boolean is12DigitNumber(String accountNo) {
		 return accountNo != null && accountNo.matches("^\\d{12}$");
	}

	@Override
	public ResponseEntity<String> updateExpenseTrasactionsRecords(int id,
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {

		boolean is12DigitNumber = is12DigitNumber(expenseTrasactionsRecordsDTO.getAccountNo());
		
		int n = 0;
		
		if(is12DigitNumber == true) {
		n = expenseTrasactionsRecordsRepository.updateExpenseTrasactionsRecords(id,
				expenseTrasactionsRecordsDTO.getDate(), expenseTrasactionsRecordsDTO.getExpenseCategory(),
				expenseTrasactionsRecordsDTO.getExpenseSubCategory(), expenseTrasactionsRecordsDTO.getAmount(),
				expenseTrasactionsRecordsDTO.getPaymentMode(), expenseTrasactionsRecordsDTO.getPaymentModeType(),
				expenseTrasactionsRecordsDTO.getByWhom(),expenseTrasactionsRecordsDTO.getAccountNo());
		}
		else
		{
			return ResponseEntity.ok("Account number must be a 12-digit number.");
		}

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
