package com.example.expense_transactions.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpenseTrasactionsRecordsDTO {
	private int id;
	
	@JsonIgnore
	private long row_no;

	private Date date;

	private String expense;

	private String type;

	private double amount;

	private String payment_mode;

	private String payment_mode_type;

	private String by_whom;

	public ExpenseTrasactionsRecordsDTO() {
		super();
	}

	public ExpenseTrasactionsRecordsDTO(int id, long row_no, Date date, String expense, String type, double amount,
			String payment_mode, String payment_mode_type, String by_whom) {
		super();
		this.id = id;
		this.row_no = row_no;
		this.date = date;
		this.expense = expense;
		this.type = type;
		this.amount = amount;
		this.payment_mode = payment_mode;
		this.payment_mode_type = payment_mode_type;
		this.by_whom = by_whom;
	}

	public ExpenseTrasactionsRecordsDTO(int id, Date date, String expense, String type, double amount,
			String payment_mode, String payment_mode_type, String by_whom) {
		super();
		this.id = id;
		this.date = date;
		this.expense = expense;
		this.type = type;
		this.amount = amount;
		this.payment_mode = payment_mode;
		this.payment_mode_type = payment_mode_type;
		this.by_whom = by_whom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getRow_no() {
		return row_no;
	}

	public void setRow_no(long row_no) {
		this.row_no = row_no;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getPayment_mode_type() {
		return payment_mode_type;
	}

	public void setPayment_mode_type(String payment_mode_type) {
		this.payment_mode_type = payment_mode_type;
	}

	public String getBy_whom() {
		return by_whom;
	}

	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}

	@Override
	public String toString() {
		return "ExpenseTrasactionsRecordsDTO [id=" + id + ", row_no=" + row_no + ", date=" + date + ", expense="
				+ expense + ", type=" + type + ", amount=" + amount + ", payment_mode=" + payment_mode
				+ ", payment_mode_type=" + payment_mode_type + ", by_whom=" + by_whom + "]";
	}
}
