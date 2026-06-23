package com.example.expense_transactions.dto;

public class AccountDetailsDTO {

	private String accountNo;

	private String accountHolderName;

	private String bankName;
	
	private double balance;
	
	public AccountDetailsDTO() {
		super();
	}


	public AccountDetailsDTO(String accountNo, String accountHolderName, String bankName, Double balance) {
		super();
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.bankName = bankName;
		this.balance = balance;
	}
	
	public AccountDetailsDTO(String accountNo, String accountHolderName, String bankName) {
		super();
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.bankName = bankName;
	}
	
	public AccountDetailsDTO(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}	

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
