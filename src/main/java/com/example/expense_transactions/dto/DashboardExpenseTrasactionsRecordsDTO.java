package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DashboardExpenseTrasactionsRecordsDTO {

	private String categoryName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String subCategoryName;

	private double amount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String byWhom;

	public DashboardExpenseTrasactionsRecordsDTO() {
		super();
	}

	public DashboardExpenseTrasactionsRecordsDTO(String categoryName, String subCategoryName, double amount,
			String byWhom) {
		super();
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.amount = amount;
		this.byWhom = byWhom;
	}

	public DashboardExpenseTrasactionsRecordsDTO(String categoryName, double amount) {
		super();
		this.categoryName = categoryName;
		this.amount = amount;
	}

	public DashboardExpenseTrasactionsRecordsDTO(String category_name, String subCategoryName, double amount) {
		super();
		this.categoryName = category_name;
		this.subCategoryName = subCategoryName;
		this.amount = amount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}
}
