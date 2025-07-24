package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class ExpensesSubCategoryDTO {

	@NonNull
	private int subCategoryId;

	@NonNull
	private int expensesCategoryId;

	@NonNull
	private String subCategoryName;

	public ExpensesSubCategoryDTO(int subCategoryId, int expensesCategoryId, String subCategoryName) {
		super();
		this.subCategoryId = subCategoryId;
		this.expensesCategoryId = expensesCategoryId;
		this.subCategoryName = subCategoryName;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public int getExpensesCategoryId() {
		return expensesCategoryId;
	}

	public void setExpensesCategoryId(int expensesCategoryId) {
		this.expensesCategoryId = expensesCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

}
