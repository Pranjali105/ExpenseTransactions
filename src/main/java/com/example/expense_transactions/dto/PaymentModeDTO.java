package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaymentModeDTO {

	@NonNull
	private int payment_mode_id;

	@NonNull
	private String payment_mode_name;

	public PaymentModeDTO(int payment_mode_id, String payment_mode_name) {
		super();
		this.payment_mode_id = payment_mode_id;
		this.payment_mode_name = payment_mode_name;
	}

	public int getPayment_mode_id() {
		return payment_mode_id;
	}

	public void setPayment_mode_id(int payment_mode_id) {
		this.payment_mode_id = payment_mode_id;
	}

	public String getPayment_mode_name() {
		return payment_mode_name;
	}

	public void setPayment_mode_name(String payment_mode_name) {
		this.payment_mode_name = payment_mode_name;
	}

	@Override
	public String toString() {
		return "payment_mode_DTO [payment_mode_id=" + payment_mode_id + ", payment_mode_name=" + payment_mode_name
				+ "]";
	}
}
