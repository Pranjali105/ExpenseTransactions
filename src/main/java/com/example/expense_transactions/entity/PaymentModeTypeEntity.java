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
	private int payment_mode_type_id;
	
	@Column(name = "payment_mode_id0")
	private int payment_mode_id;
	
	@Column(name = "type_name")
	private String type_name;

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

}
