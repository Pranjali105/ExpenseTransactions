package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TotalExpenseTrasactionsRecordsDTO {

	private String expense;

	private String type;

	private double amount;

	@JsonIgnore
	private String paid_by;

	public TotalExpenseTrasactionsRecordsDTO() {
		super();
	}

	public TotalExpenseTrasactionsRecordsDTO(String expense, String type, double amount, String paid_by) {
		super();
		this.expense = expense;
		this.type = type;
		this.amount = amount;
		this.paid_by = paid_by;
	}

	public TotalExpenseTrasactionsRecordsDTO(String expense, String type, double amount) {
		super();
		this.expense = expense;
		this.type = type;
		this.amount = amount;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaid_by() {
		return paid_by;
	}

	public void setPaid_by(String paid_by) {
		this.paid_by = paid_by;
	}
}
