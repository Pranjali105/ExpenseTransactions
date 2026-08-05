package com.example.expense_transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AutoSetupDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer id;
	private String privacategoryName;
	private String subCategoryName;
	private String accountNo;
	private double totalAmount;
	private int totalInstallment;
	private double interest;
	private String frequency;
	private int remainingInstallment;
	private double amountDeduct;
	private double remainingAmount;
	private String paymentMode;
	private String paymentModeType;
	private String byWhom;
	private String startDate;
	private String endDate;
	private String transactionDate;

	public AutoSetupDTO() {
		super();
	}

	public AutoSetupDTO(Integer id, String privacategoryName, String subCategoryName, String accountNo,
			double totalAmount, int totalInstallment, double interest, String frequency, int remainingInstallment,
			double amountDeduct, double remainingAmount, String paymentMode, String paymentModeType, String byWhom,
			String startDate, String endDate, String transactionDate) {
		super();
		this.id = id;
		this.privacategoryName = privacategoryName;
		this.subCategoryName = subCategoryName;
		this.accountNo = accountNo;
		this.totalAmount = totalAmount;
		this.totalInstallment = totalInstallment;
		this.interest = interest;
		this.frequency = frequency;
		this.remainingInstallment = remainingInstallment;
		this.amountDeduct = amountDeduct;
		this.remainingAmount = remainingAmount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactionDate = transactionDate;
	}
	
	public AutoSetupDTO(String privacategoryName, String subCategoryName, String accountNo, double totalAmount,
			int totalInstallment, double interest, String frequency, int remainingInstallment, double amountDeduct,
			double remainingAmount, String paymentMode, String paymentModeType, String byWhom, String startDate,
			String endDate, String transactionDate) {
		super();
		this.privacategoryName = privacategoryName;
		this.subCategoryName = subCategoryName;
		this.accountNo = accountNo;
		this.totalAmount = totalAmount;
		this.totalInstallment = totalInstallment;
		this.interest = interest;
		this.frequency = frequency;
		this.remainingInstallment = remainingInstallment;
		this.amountDeduct = amountDeduct;
		this.remainingAmount = remainingAmount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactionDate = transactionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrivacategoryName() {
		return privacategoryName;
	}

	public void setPrivacategoryName(String privacategoryName) {
		this.privacategoryName = privacategoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalInstallment() {
		return totalInstallment;
	}

	public void setTotalInstallment(int totalInstallment) {
		this.totalInstallment = totalInstallment;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getRemainingInstallment() {
		return remainingInstallment;
	}

	public void setRemainingInstallment(int remainingInstallment) {
		this.remainingInstallment = remainingInstallment;
	}

	public double getAmountDeduct() {
		return amountDeduct;
	}

	public void setAmountDeduct(double amountDeduct) {
		this.amountDeduct = amountDeduct;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentModeType() {
		return paymentModeType;
	}

	public void setPaymentModeType(String paymentModeType) {
		this.paymentModeType = paymentModeType;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

}
