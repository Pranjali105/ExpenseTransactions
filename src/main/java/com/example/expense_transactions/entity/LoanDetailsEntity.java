package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_details")
public class LoanDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sub_category_name")
	private String subCategoryName;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "rate_of_interest")
	private double rateOfInterest;

	public LoanDetailsEntity(int id, String subCategoryName, String bankName, double rateOfInterest) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.bankName = bankName;
		this.rateOfInterest = rateOfInterest;
	}

	public LoanDetailsEntity(String subCategoryName, String bankName, double rateOfInterest) {
		super();
		this.subCategoryName = subCategoryName;
		this.bankName = bankName;
		this.rateOfInterest = rateOfInterest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

}
