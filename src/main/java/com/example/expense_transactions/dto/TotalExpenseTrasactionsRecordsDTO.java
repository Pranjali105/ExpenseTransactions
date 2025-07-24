package com.example.expense_transactions.dto;

public class TotalExpenseTrasactionsRecordsDTO {

	private String expenseCategory;

	private String month;

	private int year;

	private String paidBy;

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

}
