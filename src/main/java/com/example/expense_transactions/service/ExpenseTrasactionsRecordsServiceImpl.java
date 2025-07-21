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

	@Override
	public ResponseEntity<List<TotalExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			String expense_category, String month, int year, String expense_sub_category, String paid_by) {

		DateDTO dateDTO = null;

		String month_no = expenseTrasactionsRecordsRepository.getMonthNumber(month);

		if (month_no != null) {
			dateDTO = expenseTrasactionsRecordsRepository.getDateStartEndDate(month_no, year);
		}

		if (dateDTO.getStart_date() != null && dateDTO.getEnd_date() != null) {
			if (expense_category != null && expense_sub_category != null && paid_by != null) {
				totalExpenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndExpenseSubCategoryAndPaidBy(
								expense_category, expense_sub_category, dateDTO.getStart_date(), dateDTO.getEnd_date(),
								paid_by);
			} else if (expense_category != null && paid_by != null) {
				totalExpenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(expense_category,
								dateDTO.getStart_date(), dateDTO.getEnd_date(), paid_by);
			} else if (expense_category != null && expense_sub_category != null) {
				totalExpenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndExpenseSubCategory(expense_category,
								expense_sub_category, dateDTO.getStart_date(), dateDTO.getEnd_date());
			} else if (expense_category != null) {
				totalExpenseTrasactionsRecordsLst = expenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategory(expense_category, dateDTO.getStart_date(),
								dateDTO.getEnd_date());
			}
		}

		if (totalExpenseTrasactionsRecordsLst == null || totalExpenseTrasactionsRecordsLst.isEmpty()) {
			throw new NullPointerException("Expense transaction records list is null or empty.");
		}
		return ResponseEntity.ok(totalExpenseTrasactionsRecordsLst);
	}
	
	@Override
	public ResponseEntity<String> addCategoryAndSubCategory(String category, String subCategory) {
		
		int n = expenseTrasactionsRecordsRepository.addCategory(category);

		int m = expenseTrasactionsRecordsRepository.addSubCategory(category, subCategory);

		if (m == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");

	}
}
