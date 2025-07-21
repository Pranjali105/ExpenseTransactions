package com.example.expense_transactions.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense_trasactions_records")
public class ExpenseTrasactionsRecordsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = "expenses_category_name")
	private String expenses_category_name;

	@Column(name = "sub_category_name")
	private String sub_category_name;

	@Column(name = "amount")
	private double amount;

	@Column(name = "payment_mode_name")
	private String payment_mode_name;

	@Column(name = "type_name")
	private String type_name;

	@Column(name = "by_whom")
	private String by_whom;

	public ExpenseTrasactionsRecordsEntity() {
		super();
	}

	public ExpenseTrasactionsRecordsEntity(int id, Date date, String expenses_category_name, String sub_category_name,
			double amount, String payment_mode_name, String type_name, String by_whom) {
		super();
		this.id = id;
		this.date = date;
		this.expenses_category_name = expenses_category_name;
		this.sub_category_name = sub_category_name;
		this.amount = amount;
		this.payment_mode_name = payment_mode_name;
		this.type_name = type_name;
		this.by_whom = by_whom;
	}

	public ExpenseTrasactionsRecordsEntity(Date date, String expenses_category_name, String sub_category_name,
			double amount, String payment_mode_name, String type_name, String by_whom) {
		super();
		this.date = date;
		this.expenses_category_name = expenses_category_name;
		this.sub_category_name = sub_category_name;
		this.amount = amount;
		this.payment_mode_name = payment_mode_name;
		this.type_name = type_name;
		this.by_whom = by_whom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpenses_category_name() {
		return expenses_category_name;
	}

	public void setExpenses_category_name(String expenses_category_name) {
		this.expenses_category_name = expenses_category_name;
	}

	public String getSub_category_name() {
		return sub_category_name;
	}

	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPayment_mode_name() {
		return payment_mode_name;
	}

	public void setPayment_mode_name(String payment_mode_name) {
		this.payment_mode_name = payment_mode_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getBy_whom() {
		return by_whom;
	}

	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}

}
