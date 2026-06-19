package com.example.expense_transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.CreditDetails;
import com.example.expense_transactions.repository.AccountDetailsRepository;
import com.example.expense_transactions.repository.CreditDetailsRepository;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

	@Autowired
	AccountDetailsRepository accountDetailsRepository;

	@Autowired
	CreditDetailsRepository creditDetailsRepository;

	@Override
	public ResponseEntity<AccountDetailsDTO> getAccountDetails(String accountNo) {

		AccountDetailsDTO accountDetailsDTOLst = null;

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
	public ResponseEntity<String> addCreditDetails(CreditDetails creditDetails) {

		int n = 0;

		boolean is12DigitNumber = is12DigitNumber(creditDetails.getAccount_no());

		if (is12DigitNumber == true) {
			n = creditDetailsRepository.addCreditDetails(creditDetails.getAccount_no(), creditDetails.getAmount(),
					creditDetails.getTransaction_type());
		} else {
			return ResponseEntity.ok("Account number must be a 12-digit number.");
		}

		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
		{
			return ResponseEntity.ok("Data inserted successfully");
		}
	}

}
