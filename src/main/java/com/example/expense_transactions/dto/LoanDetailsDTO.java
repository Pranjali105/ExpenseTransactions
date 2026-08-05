package com.example.expense_transactions.dto;

public class LoanDetailsDTO {

	private String subCategoryName;
	private String bankName;
	private double rateOfInterest;

	public LoanDetailsDTO(String subCategoryName, String bankName, double rateOfInterest) {
		super();
		this.subCategoryName = subCategoryName;
		this.bankName = bankName;
		this.rateOfInterest = rateOfInterest;
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
