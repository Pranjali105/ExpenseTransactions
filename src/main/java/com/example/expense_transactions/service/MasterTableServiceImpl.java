package com.example.expense_transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.CategorySubCategoryDTO;
import com.example.expense_transactions.repository.MasterTableRepository;

@Service
public class MasterTableServiceImpl implements MasterTableService {

	@Autowired
	MasterTableRepository masterTableRepository;

	@Override
	public ResponseEntity<String> addCategoryAndSubCategory(CategorySubCategoryDTO categorySubCategoryDTO) {

		masterTableRepository.addCategory(categorySubCategoryDTO.getCategory());

		int m = masterTableRepository.addSubCategory(categorySubCategoryDTO.getCategory(),
				categorySubCategoryDTO.getSubCategory());

		if (m == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");

	}

	@Override
	public ResponseEntity<String> addAccountDetails(AccountDetailsDTO accountDetailsDTO) {

		String accountNumber = accountDetailsDTO.getAccountNo();

		boolean is12DigitNumber = is12DigitNumber(accountNumber);
		int n = 0;

		if (is12DigitNumber == true) {
			if (accountDetailsDTO.getBalance() >= 10000) {
				accountDetailsDTO.setBankName(accountDetailsDTO.getBankName().toUpperCase());
				n = masterTableRepository.addAccountDetails(accountDetailsDTO.getAccountNo(),
						accountDetailsDTO.getAccountHolderName(), accountDetailsDTO.getBankName(), accountDetailsDTO.getBalance());
			} else {
				return ResponseEntity.ok("Account munst have minimun 10,000 Rs balance in the account");
			}
		} else {
			return ResponseEntity.ok("Account number must be a 12-digit number");
		}
		if (n == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else

			return ResponseEntity.ok("Data inserted successfully");
	}

	public static boolean is12DigitNumber(String accountNumber) {
		return accountNumber != null && accountNumber.matches("^\\d{12}$");
	}

}
