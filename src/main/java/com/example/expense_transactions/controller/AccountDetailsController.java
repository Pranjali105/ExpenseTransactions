package com.example.expense_transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.CreditDetails;
import com.example.expense_transactions.service.AccountDetailsService;

@RestController
public class AccountDetailsController {
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	ResponseEntity<AccountDetailsDTO> accountDetailsDTOLst = null;
	
	ResponseEntity<String> addCreditDetails = null;
	
	@GetMapping(value = "/getAccountDetails/{accountNo}")
	public ResponseEntity<AccountDetailsDTO> getAccountDetails(@PathVariable(required = true) String accountNo)
	{
		accountDetailsDTOLst = accountDetailsService.getAccountDetails(accountNo);
		
		return accountDetailsDTOLst;
		
	}
	
	@PostMapping(value = "/addCreditDetails")
	public ResponseEntity<String> addCreditDetails(@RequestBody CreditDetails creditDetails)
	{
		if(creditDetails != null)
		{
			addCreditDetails = accountDetailsService.addCreditDetails(creditDetails);
		}
		return addCreditDetails;
		
	}

}
