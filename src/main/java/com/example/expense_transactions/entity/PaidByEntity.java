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
	private int paidById;

	@Column(name = "by_whom")
	private String byWhom;

	public int getPaidById() {
		return paidById;
	}

	public void setPaidById(int paidById) {
		this.paidById = paidById;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

}
