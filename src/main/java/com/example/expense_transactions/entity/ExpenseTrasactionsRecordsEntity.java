package com.example.expense_transactions.entity;

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
	private String date;

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
	
	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "transaction_status")
	private String transaction_status;

	public ExpenseTrasactionsRecordsEntity() {
		super();
	}

	public ExpenseTrasactionsRecordsEntity(int id, String date, String expensesCategoryName, String subCategoryName,
			double amount, String paymentModeName, String typeName, String byWhom, String accountNo,
			String transaction_status) {
		super();
		this.id = id;
		this.date = date;
		this.expensesCategoryName = expensesCategoryName;
		this.subCategoryName = subCategoryName;
		this.amount = amount;
		this.paymentModeName = paymentModeName;
		this.typeName = typeName;
		this.byWhom = byWhom;
		this.accountNo = accountNo;
		this.transaction_status = transaction_status;
	}

	public ExpenseTrasactionsRecordsEntity(String date, String expensesCategoryName, String subCategoryName,
			double amount, String paymentModeName, String typeName, String byWhom, String accountNo) {
		super();
		this.date = date;
		this.expensesCategoryName = expensesCategoryName;
		this.subCategoryName = subCategoryName;
		this.amount = amount;
		this.paymentModeName = paymentModeName;
		this.typeName = typeName;
		this.byWhom = byWhom;
		this.accountNo = accountNo;
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
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

}
