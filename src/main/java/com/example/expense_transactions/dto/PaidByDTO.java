package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaidByDTO {

	@NonNull
	private int paid_by_id;

	@NonNull
	private String by_whom;

	public PaidByDTO(int paid_by_id, String by_whom) {
		super();
		this.paid_by_id = paid_by_id;
		this.by_whom = by_whom;
	}

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

	@Override
	public String toString() {
		return "paid_by_DTO [paid_by_id=" + paid_by_id + ", by_whom=" + by_whom + "]";
	}
}
