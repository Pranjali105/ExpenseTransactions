package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_mode")
public class PaymentModeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_mode_id;

	@Column(name = "payment_mode_name")
	private String payment_mode_name;

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

}
