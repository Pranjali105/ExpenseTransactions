package com.example.expense_transactions.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpenseTrasactionsRecordsDTO {
	private int id;

	@JsonIgnore
	private long rowNo;

	private Date date;

	private String expenseCategory;

	private String expenseSubCategory;

	private double amount;

	private String paymentMode;

	private String paymentModeType;

	private String byWhom;

	public ExpenseTrasactionsRecordsDTO() {
		super();
	}

	public ExpenseTrasactionsRecordsDTO(int id, long rowNo, Date date, String expense, String type, double amount,
			String paymentMode, String paymentModeType, String byWhom) {
		super();
		this.id = id;
		this.rowNo = rowNo;
		this.date = date;
		this.expenseCategory = expense;
		this.expenseSubCategory = type;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
	}

	public ExpenseTrasactionsRecordsDTO(int id, Date date, String expense, String type, double amount,
			String paymentMode, String paymentModeType, String byWhom) {
		super();
		this.id = id;
		this.date = date;
		this.expenseCategory = expense;
		this.expenseSubCategory = type;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getRowNo() {
		return rowNo;
	}

	public void setRowNo(long rowNo) {
		this.rowNo = rowNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	public String getExpenseSubCategory() {
		return expenseSubCategory;
	}

	public void setExpenseSubCategory(String expenseSubCategory) {
		this.expenseSubCategory = expenseSubCategory;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentModeType() {
		return paymentModeType;
	}

	public void setPaymentModeType(String paymentModeType) {
		this.paymentModeType = paymentModeType;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

}
