package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_details")
public class CreditDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date")
	private String date;

	@Column(name = "account_no")
	private String account_no;

	@Column(name = "amount")
	private double amount;

	@Column(name = "transaction_type")
	private String transaction_type;

	@Column(name = "transaction_status")
	private String transaction_status;

	public CreditDetailsEntity(String date, String account_no, double amount, String transaction_type,
			String transaction_status) {
		super();
		this.date = date;
		this.account_no = account_no;
		this.amount = amount;
		this.transaction_type = transaction_type;
		this.transaction_status = transaction_status;
	}

	public CreditDetailsEntity(int id, String account_no, double amount, String transaction_type) {
		super();
		this.id = id;
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
