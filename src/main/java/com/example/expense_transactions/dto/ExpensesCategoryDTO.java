package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class ExpensesCategoryDTO {

	@NonNull
	private int expensesCategoryId;

	@NonNull
	private String expensesCategoryName;
	
	public ExpensesCategoryDTO(int expensesCategoryId, String expensesCategoryName) {
		super();
		this.expensesCategoryId = expensesCategoryId;
		this.expensesCategoryName = expensesCategoryName;
	}

	public int getExpensesCategoryId() {
		return expensesCategoryId;
	}

	public void setExpensesCategoryId(int expensesCategoryId) {
		this.expensesCategoryId = expensesCategoryId;
	}

	public String getExpensesCategoryName() {
		return expensesCategoryName;
	}

	public void setExpensesCategoryName(String expensesCategoryName) {
		this.expensesCategoryName = expensesCategoryName;
	}
	
}
