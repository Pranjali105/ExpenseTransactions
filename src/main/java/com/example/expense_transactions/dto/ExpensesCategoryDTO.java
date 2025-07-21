package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class ExpensesCategoryDTO {

	@NonNull
	private int expenses_category_id;

	@NonNull
	private String expenses_category_name;

	public ExpensesCategoryDTO(int expenses_category_id, String expenses_category_name) {
		super();
		this.expenses_category_id = expenses_category_id;
		this.expenses_category_name = expenses_category_name;
	}

	public int getexpenses_category_id() {
		return expenses_category_id;
	}

	public void setexpenses_category_name_id(int expenses_category_name_id) {
		this.expenses_category_id = expenses_category_name_id;
	}

	public String getExpenses_category_name() {
		return expenses_category_name;
	}

	public void setExpenses_category_name(String expenses_category_name) {
		this.expenses_category_name = expenses_category_name;
	}

	@Override
	public String toString() {
		return "expenses_category_name_DTO [expenses_category_name_id=" + expenses_category_id + ", expenses_category_name="
				+ expenses_category_name + "]";
	}

}
