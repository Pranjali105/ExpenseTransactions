package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DashboardExpenseCategoryTrasactionsRecordsDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String categoryName;

	private double amount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String byWhom;

	public DashboardExpenseCategoryTrasactionsRecordsDTO() {
		super();
	}

	public DashboardExpenseCategoryTrasactionsRecordsDTO(String categoryName, double amount, String byWhom) {
		super();
		this.categoryName = categoryName;
		this.amount = amount;
		this.byWhom = byWhom;
	}

	 DashboardExpenseCategoryTrasactionsRecordsDTO(String category_name, double amount) {
		super();
		this.categoryName = category_name;
		this.amount = amount;
	}

	/*
	 * DashboardExpenseCategoryTrasactionsRecordsDTO(double amount, String byWhom) {
	 * super(); this.amount = amount; this.byWhom = byWhom; }
	 */

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

}
