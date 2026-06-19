package com.example.expense_transactions.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.AccountDetailsRepository;
import com.example.expense_transactions.repository.ExpenseTrasactionsRecordsRepository;

@Service
public class ExpenseTrasactionsRecordsServiceImpl implements ExpenseTrasactionsRecordsService {

	private final AccountDetailsRepository accountDetailsRepository;

	@Autowired
	ExpenseTrasactionsRecordsRepository expenseTrasactionsRecordsRepository;

	@Autowired
	AccountDetailsService accountDetailsService;

	ResponseEntity<AccountDetailsDTO> accountDetailsDTO;
	
	Date sqlDate;

	List<ExpenseTrasactionsRecordsDTO> expenseTrasactionsRecordsLst = new ArrayList<ExpenseTrasactionsRecordsDTO>();

	ExpenseTrasactionsRecordsServiceImpl(AccountDetailsRepository accountDetailsRepository) {
		this.accountDetailsRepository = accountDetailsRepository;
	}

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
		int n = 0, m = 0;

		double balance_amount = 0, updatedBalance = 0;

		if (is12DigitNumber == true) {

			accountDetailsDTO = accountDetailsService.getAccountDetails(expenseTrasactionsRecordsDTO.getAccountNo());

			balance_amount = accountDetailsDTO.getBody().getBalance();

			updatedBalance = updateBalanceRecord(balance_amount, expenseTrasactionsRecordsDTO.getAmount());

			if (updatedBalance != 0) {

				m = accountDetailsRepository.updateAccountDetails(expenseTrasactionsRecordsDTO.getAccountNo(),
						updatedBalance);
				if (m != 0) {
					
					sqlDate = getCurrentTimestampdate();
					
					n = expenseTrasactionsRecordsRepository.addExpenseTrasactionsRecords(
							sqlDate, expenseTrasactionsRecordsDTO.getExpenseCategory(),
							expenseTrasactionsRecordsDTO.getExpenseSubCategory(),
							expenseTrasactionsRecordsDTO.getAmount(), expenseTrasactionsRecordsDTO.getPaymentMode(),
							expenseTrasactionsRecordsDTO.getPaymentModeType(), expenseTrasactionsRecordsDTO.getByWhom(),
							expenseTrasactionsRecordsDTO.getAccountNo());
				} else {
					return ResponseEntity.ok("Error occured while inserting the data");
				}
			} else {
				accountDetailsRepository.updateAccountDetails(expenseTrasactionsRecordsDTO.getAccountNo(),
						balance_amount);
				return ResponseEntity.ok("Balance is not sufficient to do the transaction");
			}
		} else {
			return ResponseEntity.ok("Account number must be a 12-digit number.");
		}
		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");
	}

	private Date getCurrentTimestampdate() {
		
		 // 1. Get current java.sql.Timestamp
        Timestamp timestamp = Timestamp.from(Instant.now());
        System.out.println("timestamp" + timestamp); 
        
		// Define the pattern matching your input string
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String formattedDate = sdf.format(timestamp);
		 
		 System.out.println("formattedDate" + formattedDate);
		 
		// Parse into modern LocalDate
	        LocalDate localDate = LocalDate.parse(formattedDate);
	     
	        System.out.println("localDate" + localDate);
	        
	        // Convert to legacy java.sql.Date
	        sqlDate = java.sql.Date.valueOf(localDate);
	        
	        System.out.println("localDate" + localDate);
		
		return sqlDate;
	}

	private boolean is12DigitNumber(String accountNo) {
		 return accountNo != null && accountNo.matches("^\\d{12}$");
	}

	private double updateBalanceRecord(double balance_amount, double amount) {
		double balance = 0;
		if (balance_amount >= amount) {
			balance = balance_amount - amount;
			accountDetailsDTO.getBody().setBalance(balance);
		}

		return balance;
	}

	@Override
	public ResponseEntity<String> updateExpenseTrasactionsRecords(int id,
			ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {

		boolean is12DigitNumber = is12DigitNumber(expenseTrasactionsRecordsDTO.getAccountNo());
		
		int n = 0;

		if (is12DigitNumber == true) {
			n = expenseTrasactionsRecordsRepository.updateExpenseTrasactionsRecords(id,
					expenseTrasactionsRecordsDTO.getDate(), expenseTrasactionsRecordsDTO.getExpenseCategory(),
					expenseTrasactionsRecordsDTO.getExpenseSubCategory(), expenseTrasactionsRecordsDTO.getAmount(),
					expenseTrasactionsRecordsDTO.getPaymentMode(), expenseTrasactionsRecordsDTO.getPaymentModeType(),
					expenseTrasactionsRecordsDTO.getByWhom(), expenseTrasactionsRecordsDTO.getAccountNo());
		} else {
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
