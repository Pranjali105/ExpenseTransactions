package com.example.expense_transactions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auto_setup_payment")
public class AutoSetupPaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "sub_category_name")
	private String subCategoryName;

	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "amount")
	private double amount;

	@Column(name = "tenure")
	private Integer tenure;

	@Column(name = "rate_of_interest")
	private Double rateOfInterest;

	@Column(name = "frequency")
	private String frequency;

	@Column(name = "emi_amount")
	private Double EMIAmount;

	@Column(name = "remaining_installment")
	private Integer remainingInstallment;

	@Column(name = "remaining_amount")
	private Double remainingAmount;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "payment_mode_type")
	private String paymentModeType;

	@Column(name = "by_whom")
	private String byWhom;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "transaction_date")
	private String transactionDate;

	@Column(name = "loan_status")
	private String loanStatus;

	public AutoSetupPaymentEntity() {
		super();
	}

	public AutoSetupPaymentEntity(String categoryName, String subCategoryName, String accountNo, String bankName,
			double amount, Integer tenure, Double rateOfInterest, String frequency, Double EMIAmount,
			Integer remainingInstallment, Double remainingAmount, String paymentMode, String paymentModeType, String byWhom,
			String startDate, String endDate, String transactionDate, String loanStatus) {
		super();
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.amount = amount;
		this.tenure = tenure;
		this.rateOfInterest = rateOfInterest;
		this.frequency = frequency;
		this.EMIAmount = EMIAmount;
		this.remainingInstallment = remainingInstallment;
		this.remainingAmount = remainingAmount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactionDate = transactionDate;
		this.loanStatus = loanStatus;
	}

	public AutoSetupPaymentEntity(Integer id, String categoryName, String subCategoryName, String accountNo,
			String bankName, double amount, Integer tenure, Double rateOfInterest, String frequency,
			Double EMIAmount, Integer remainingInstallment, Double remainingAmount, String paymentMode,
			String paymentModeType, String byWhom, String startDate, String endDate, String transactionDate,
			String loanStatus) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.amount = amount;
		this.tenure = tenure;
		this.rateOfInterest = rateOfInterest;
		this.frequency = frequency;
		this.EMIAmount = EMIAmount;
		this.remainingInstallment = remainingInstallment;
		this.remainingAmount = remainingAmount;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.byWhom = byWhom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactionDate = transactionDate;
		this.loanStatus = loanStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Double getEMIAmount() {
		return EMIAmount;
	}

	public void setEMIAmount(Double eMIAmount) {
		EMIAmount = eMIAmount;
	}

	public Integer getTenure() {
		return tenure;
	}
	
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	
	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getRemainingInstallment() {
		return remainingInstallment;
	}

	public void setRemainingInstallment(Integer remainingInstallment) {
		this.remainingInstallment = remainingInstallment;
	}

	public Double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Double remainingAmount) {
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

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

}
