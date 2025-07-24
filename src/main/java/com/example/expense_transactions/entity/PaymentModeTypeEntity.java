package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_mode_type")
public class PaymentModeTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentModeTypeId;

	@Column(name = "payment_mode_id0")
	private int paymentModeId;

	@Column(name = "type_name")
	private String typeName;

	public int getPaymentModeTypeId() {
		return paymentModeTypeId;
	}

	public void setPaymentModeTypeId(int paymentModeTypeId) {
		this.paymentModeTypeId = paymentModeTypeId;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
