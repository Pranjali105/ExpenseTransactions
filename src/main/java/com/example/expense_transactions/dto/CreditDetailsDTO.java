package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CreditDetailsDTO {
	
	private int id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private String date;
	
	private String account_no;
	
	private double amount;
	
	private String transaction_type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String transaction_status;

	public CreditDetailsDTO() {
		super();
	}
	
	public CreditDetailsDTO(String date, String account_no, double amount, String transaction_type,
			String transaction_status) {
		super();
		this.date = date;
		this.account_no = account_no;
		this.amount = amount;
		this.transaction_type = transaction_type;
		this.transaction_status = transaction_status;
	}

	public CreditDetailsDTO(String account_no, double amount, String transaction_type) {
		super();
		this.account_no = account_no;
		this.amount = amount;
		this.transaction_type = transaction_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

}
