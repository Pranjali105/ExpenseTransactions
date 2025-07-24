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
	private String expensesCategoryName;

	@Column(name = "sub_category_name")
	private String subCategoryName;

	@Column(name = "amount")
	private double amount;

	@Column(name = "payment_mode_name")
	private String paymentModeName;

	@Column(name = "type_name")
	private String typeName;

	@Column(name = "by_whom")
	private String byWhom;

	public ExpenseTrasactionsRecordsEntity() {
		super();
	}

	public ExpenseTrasactionsRecordsEntity(Date date, String expensesCategoryName, String subCategoryName,
			double amount, String paymentModeName, String typeName, String byWhom) {
		super();
		this.date = date;
		this.expensesCategoryName = expensesCategoryName;
		this.subCategoryName = subCategoryName;
		this.amount = amount;
		this.paymentModeName = paymentModeName;
		this.typeName = typeName;
		this.byWhom = byWhom;
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

	public String getExpensesCategoryName() {
		return expensesCategoryName;
	}

	public void setExpensesCategoryName(String expensesCategoryName) {
		this.expensesCategoryName = expensesCategoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentModeName() {
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

}
