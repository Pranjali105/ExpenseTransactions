package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaymentModeTypeDTO {

	@NonNull
	private int payment_mode_type_id;

	@NonNull
	private int payment_mode_id;

	@NonNull
	private String type_name;

	public PaymentModeTypeDTO(int payment_mode_type_id, int payment_mode_id, String type_name) {
		super();
		this.payment_mode_type_id = payment_mode_type_id;
		this.payment_mode_id = payment_mode_id;
		this.type_name = type_name;
	}

	public int getPayment_mode_type_id() {
		return payment_mode_type_id;
	}

	public void setPayment_mode_type_id(int payment_mode_type_id) {
		this.payment_mode_type_id = payment_mode_type_id;
	}

	public int getPayment_mode_id() {
		return payment_mode_id;
	}

	public void setPayment_mode_id(int payment_mode_id) {
		this.payment_mode_id = payment_mode_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String toString() {
		return "payment_mode_type_DTO [payment_mode_type_id=" + payment_mode_type_id + ", payment_mode_id="
				+ payment_mode_id + ", type_name=" + type_name + "]";
	}

}
