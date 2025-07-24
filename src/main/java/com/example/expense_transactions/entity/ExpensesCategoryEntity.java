package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses_category")
public class ExpensesCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expensesCategoryId;

	@Column(name = "expenses_category_name")
	private String expensesCategoryName;

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
