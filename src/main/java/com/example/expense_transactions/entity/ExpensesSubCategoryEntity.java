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
	private int sub_category_id;

	@Column(name = "expenses_category_id")
	private int expenses_category_id;

	@Column(name = "sub_category_name")
	private String sub_category_name;

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

}
