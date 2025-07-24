package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaidByDTO {

	@NonNull
	private int paidById;

	@NonNull
	private String byWhom;

	public PaidByDTO(int paidById, String byWhom) {
		super();
		this.paidById = paidById;
		this.byWhom = byWhom;
	}

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
