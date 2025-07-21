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
	private int expenses_category_id;

	@Column(name = "expenses_category_name")
	private String expenses_category_name;

	public int getExpenses_category_id() {
		return expenses_category_id;
	}

	public void setExpenses_category_id(int expenses_category_id) {
		this.expenses_category_id = expenses_category_id;
	}

	public String getExpenses_category_name() {
		return expenses_category_name;
	}

	public void setExpenses_category_name(String expenses_category_name) {
		this.expenses_category_name = expenses_category_name;
	}
}
