package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DashboardExpenseTrasactionsRecordsDTO {

	private String category_name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String subCategory_name;

	private double amount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String by_whom;

	public DashboardExpenseTrasactionsRecordsDTO() {
		super();
	}

	public DashboardExpenseTrasactionsRecordsDTO(String category_name, String subCategory_name, double amount,
			String by_whom) {
		super();
		this.category_name = category_name;
		this.subCategory_name = subCategory_name;
		this.amount = amount;
		this.by_whom = by_whom;
	}

	public DashboardExpenseTrasactionsRecordsDTO(String category_name, double amount) {
		super();
		this.category_name = category_name;
		this.amount = amount;
	}

	public DashboardExpenseTrasactionsRecordsDTO(String category_name, String subCategory_name, double amount) {
		super();
		this.category_name = category_name;
		this.subCategory_name = subCategory_name;
		this.amount = amount;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getSubCategory_name() {
		return subCategory_name;
	}

	public void setSubCategory_name(String subCategory_name) {
		this.subCategory_name = subCategory_name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBy_whom() {
		return by_whom;
	}

	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}

}
