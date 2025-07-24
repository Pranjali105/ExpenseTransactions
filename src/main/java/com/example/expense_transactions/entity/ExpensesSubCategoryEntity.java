package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses_sub_category")
public class ExpensesSubCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subCategoryId;

	@Column(name = "expenses_category_id")
	private int expensesCategoryId;

	@Column(name = "sub_category_name")
	private String subCategoryName;

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
