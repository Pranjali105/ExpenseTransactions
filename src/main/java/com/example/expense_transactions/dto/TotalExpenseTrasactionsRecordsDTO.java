package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TotalExpenseTrasactionsRecordsDTO {

	private String expenseCategory;

	private String month;

	private int year;

	private String paidBy;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String accountNo;
	
	public TotalExpenseTrasactionsRecordsDTO() {
		super();
	}
	
	public TotalExpenseTrasactionsRecordsDTO(String expenseCategory, String month, int year, String paidBy,
			String accountNo) {
		super();
		this.expenseCategory = expenseCategory;
		this.month = month;
		this.year = year;
		this.paidBy = paidBy;
		this.accountNo = accountNo;
	}

	public TotalExpenseTrasactionsRecordsDTO(String expenseCategory, String month, int year, String paidBy) {
		super();
		this.expenseCategory = expenseCategory;
		this.month = month;
		this.year = year;
		this.paidBy = paidBy;
	}

	public String getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
