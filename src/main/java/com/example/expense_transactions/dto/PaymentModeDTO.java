package com.example.expense_transactions.dto;

import org.springframework.lang.NonNull;

public class PaymentModeDTO {

	@NonNull
	private int paymentModeId;

	@NonNull
	private String paymentModeName;

	public PaymentModeDTO(int paymentModeId, String paymentModeName) {
		super();
		this.paymentModeId = paymentModeId;
		this.paymentModeName = paymentModeName;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getPaymentModeName() {
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}

}
