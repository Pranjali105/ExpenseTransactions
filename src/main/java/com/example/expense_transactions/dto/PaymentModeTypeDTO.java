package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaymentModeTypeDTO {

	@NonNull
	private int paymentModeTypeId;

	@NonNull
	private int paymentMdeId;

	@NonNull
	private String typeName;

	public PaymentModeTypeDTO(int paymentModeTypeId, int paymentMdeId, String typeName) {
		super();
		this.paymentModeTypeId = paymentModeTypeId;
		this.paymentMdeId = paymentMdeId;
		this.typeName = typeName;
	}

	public int getPaymentModeTypeId() {
		return paymentModeTypeId;
	}

	public void setPaymentModeTypeId(int paymentModeTypeId) {
		this.paymentModeTypeId = paymentModeTypeId;
	}

	public int getPaymentMdeId() {
		return paymentMdeId;
	}

	public void setPaymentMdeId(int paymentMdeId) {
		this.paymentMdeId = paymentMdeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
