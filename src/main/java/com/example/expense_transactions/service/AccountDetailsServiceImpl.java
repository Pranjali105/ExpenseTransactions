package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.AccountPassbookDTO;
import com.example.expense_transactions.dto.CreditDetailsDTO;
import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.AccountDetailsRepository;
import com.example.expense_transactions.repository.CreditDetailsRepository;
import com.example.expense_transactions.repository.ExpenseTrasactionsRecordsRepository;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

	@Autowired
	AccountDetailsRepository accountDetailsRepository;

	@Autowired
	CreditDetailsRepository creditDetailsRepository;

	@Autowired
	ExpenseTrasactionsRecordsRepository expenseTrasactionsRecordsRepository;

	AccountDetailsDTO accountDetailsDTOLst = null;

	@Override
	public ResponseEntity<AccountDetailsDTO> getAccountDetails(String accountNo) {

		boolean is12DigitNumber = is12DigitNumber(accountNo);

		if (is12DigitNumber == true) {
			accountDetailsDTOLst = accountDetailsRepository.getAccountDetails(accountNo);
		}

		if (accountDetailsDTOLst == null) {
			throw new NullPointerException("Account Details list is null or empty.");
		}
		return ResponseEntity.ok(accountDetailsDTOLst);
	}

	private boolean is12DigitNumber(String accountNo) {
		return accountNo != null && accountNo.matches("^\\d{12}$");
	}

	@Override
	public ResponseEntity<String> addCreditDetails(CreditDetailsDTO creditDetailsDTO) {

		int n = 0;

		boolean is12DigitNumber = is12DigitNumber(creditDetailsDTO.getAccount_no());

		if (is12DigitNumber == true) {

			n = creditDetailsRepository.addCreditDetails(creditDetailsDTO.getAccount_no(), creditDetailsDTO.getAmount(),
					creditDetailsDTO.getTransaction_type());
		} else {
			return ResponseEntity.ok("Account number must be a 12-digit number.");
		}

		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else {

			accountDetailsDTOLst = accountDetailsRepository.getAccountDetails(creditDetailsDTO.getAccount_no());

			double updated_balance = accountDetailsDTOLst.getBalance() + creditDetailsDTO.getAmount();

			accountDetailsDTOLst.setBalance(updated_balance);

			return ResponseEntity.ok("Data inserted successfully");
		}
	}

	@Override
	public ResponseEntity<List<AccountPassbookDTO>> getAllAccountTransactionRecords(
			AccountDetailsDTO accountDetailsDTO) {

		// 1. Guard Clause for validation
		if (accountDetailsDTO == null || !is12DigitNumber(accountDetailsDTO.getAccountNo())) {
			throw new IllegalArgumentException("Account number must be a 12-digit number."); // Changed to
																								// IllegalArgumentException
		}

		// 2. Fetch details (Assuming this returns a single object based on your logic.
		// If it returns a List, adapt accordingly)
		AccountDetailsDTO actualAccountDetails = accountDetailsRepository
				.getAccountDetails(accountDetailsDTO.getAccountNo());

		if (actualAccountDetails == null) {
			throw new NullPointerException("Account Details not found for the provided account number.");
		}

		// 3. Securely validate account details using .equals()
		boolean isVerified = actualAccountDetails.getAccountNo().equals(accountDetailsDTO.getAccountNo())
				&& actualAccountDetails.getAccountHolderName().equals(accountDetailsDTO.getAccountHolderName())
				&& actualAccountDetails.getBankName().equals(accountDetailsDTO.getBankName());

		// Initialize list to avoid NullPointerException
		List<AccountPassbookDTO> accountPassbookLst = new ArrayList<>();
		double balance = 10000.0;

		if (isVerified) {
			List<ExpenseTrasactionsRecordsDTO> expenseRecords = expenseTrasactionsRecordsRepository
					.getAllExpenseTransactionRecords(accountDetailsDTO.getAccountNo());

			List<CreditDetailsDTO> creditRecords = creditDetailsRepository
					.getAllCreditDetailsRecords(accountDetailsDTO.getAccountNo());

			// Process Credits
			if (creditRecords != null) {
				for (CreditDetailsDTO credit : creditRecords) {
					AccountPassbookDTO passbookEntry = new AccountPassbookDTO(); // New instance per iteration
					passbookEntry.setAccount_no(credit.getAccount_no());
					passbookEntry.setTransaction_date(credit.getDate());
					passbookEntry.setTransaction_description(credit.getTransaction_type());
					passbookEntry.setDeposit_amount(credit.getAmount());
					passbookEntry.setWithdrwal_amount(null);

					if ("Credited".equals(credit.getTransaction_status())) {
						balance += credit.getAmount();
					}
					passbookEntry.setId(null);
					passbookEntry.setBalance(balance);
					accountPassbookLst.add(passbookEntry);
				}
			}

			// Process Expenses
			if (expenseRecords != null) {
				for (ExpenseTrasactionsRecordsDTO expense : expenseRecords) {
					AccountPassbookDTO passbookEntry = new AccountPassbookDTO(); // New instance per iteration
					passbookEntry.setAccount_no(expense.getAccountNo());
					passbookEntry.setTransaction_date(expense.getDate()); // Fixed date typo
					passbookEntry
							.setTransaction_description(expense.getPaymentMode() + "-" + expense.getPaymentModeType());
					passbookEntry.setWithdrwal_amount(expense.getAmount());
					passbookEntry.setDeposit_amount(null);

					if ("Debited".equals(expense.getTransaction_status())) {
						balance -= expense.getAmount();
					}
					
					passbookEntry.setId(null);
					passbookEntry.setBalance(balance);
					accountPassbookLst.add(passbookEntry);
				}
			}

			// 4. Sort by Date safely (Assuming getTransaction_date returns a Comparable
			// type like LocalDate/Date)
			accountPassbookLst.sort(Comparator.comparing(AccountPassbookDTO::getTransaction_date, Comparator.naturalOrder()));
		}

		return ResponseEntity.ok(accountPassbookLst);
	}

}
