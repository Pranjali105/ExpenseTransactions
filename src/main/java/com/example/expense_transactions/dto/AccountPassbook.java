package com.example.expense_transactions.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AccountPassbook {
	
	//rowno
	private int id;
	
	private Date transaction_date;
	
	private String transaction_description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String withdrwal_amount;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String deposit_amount;
	
	private double balance;

	public AccountPassbook(int id, Date transaction_date, String transaction_description, String withdrwal_amount,
			String deposit_amount, double balance) {
		super();
		this.id = id;
		this.transaction_date = transaction_date;
		this.transaction_description = transaction_description;
		this.withdrwal_amount = withdrwal_amount;
		this.deposit_amount = deposit_amount;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getTransaction_description() {
		return transaction_description;
	}

	public void setTransaction_description(String transaction_description) {
		this.transaction_description = transaction_description;
	}

	public String getWithdrwal_amount() {
		return withdrwal_amount;
	}

	public void setWithdrwal_amount(String withdrwal_amount) {
		this.withdrwal_amount = withdrwal_amount;
	}

	public String getDeposit_amount() {
		return deposit_amount;
	}

	public void setDeposit_amount(String deposit_amount) {
		this.deposit_amount = deposit_amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
