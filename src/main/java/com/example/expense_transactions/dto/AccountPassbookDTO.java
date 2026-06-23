package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AccountPassbookDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer id;

	private String transaction_date;

	private String account_no;

	private String transaction_description;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double withdrwal_amount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double deposit_amount;

	private double balance;

	public AccountPassbookDTO() {
		super();
	}

	public AccountPassbookDTO(Integer id, String transaction_date, String account_no, String transaction_description,
			Double withdrwal_amount, Double deposit_amount, double balance) {
		super();
		this.id = id;
		this.transaction_date = transaction_date;
		this.account_no = account_no;
		this.transaction_description = transaction_description;
		this.withdrwal_amount = withdrwal_amount;
		this.deposit_amount = deposit_amount;
		this.balance = balance;
	}

	public AccountPassbookDTO(Integer id, String transaction_date, String transaction_description, Double withdrwal_amount,
			Double deposit_amount, double balance) {
		super();
		this.id = id;
		this.transaction_date = transaction_date;
		this.transaction_description = transaction_description;
		this.withdrwal_amount = withdrwal_amount;
		this.deposit_amount = deposit_amount;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getTransaction_description() {
		return transaction_description;
	}

	public void setTransaction_description(String transaction_description) {
		this.transaction_description = transaction_description;
	}

	public Double getWithdrwal_amount() {
		return withdrwal_amount;
	}

	public void setWithdrwal_amount(Double withdrwal_amount) {
		this.withdrwal_amount = withdrwal_amount;
	}

	public Double getDeposit_amount() {
		return deposit_amount;
	}

	public void setDeposit_amount(Double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

}
