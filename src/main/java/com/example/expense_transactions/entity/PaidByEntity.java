package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paid_by")
public class PaidByEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paid_by_id;

	@Column(name = "by_whom")
	private String by_whom;

	public int getPaid_by_id() {
		return paid_by_id;
	}

	public void setPaid_by_id(int paid_by_id) {
		this.paid_by_id = paid_by_id;
	}

	public String getBy_whom() {
		return by_whom;
	}

	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}

}
