package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class ExpensesSubCategoryDTO {

	@NonNull
	private int sub_category_id;

	@NonNull
	private int expenses_category_id;

	@NonNull
	private String sub_category_name;

	public ExpensesSubCategoryDTO(int sub_category_id, int expenses_category_id, String sub_category_name) {
		super();
		this.sub_category_id = sub_category_id;
		this.expenses_category_id = expenses_category_id;
		this.sub_category_name = sub_category_name;
	}

	public int getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public int getExpenses_category_id() {
		return expenses_category_id;
	}

	public void setExpenses_category_id(int expenses_category_id) {
		this.expenses_category_id = expenses_category_id;
	}

	public String getSub_category_name() {
		return sub_category_name;
	}

	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}

	@Override
	public String toString() {
		return "expenses_sub_category_DTO [sub_category_id=" + sub_category_id + ", expenses_category_id="
				+ expenses_category_id + ", sub_category_name=" + sub_category_name + "]";
	}

}
