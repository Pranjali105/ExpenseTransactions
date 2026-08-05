package com.example.expense_transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.AutoSetupDTO;
import com.example.expense_transactions.service.autoSetupService;

@RestController
public class AutoSetupPaymentController {
	
	@Autowired
	autoSetupService autoSetupService;

	@PostMapping(value = "/addSetupAutoPayment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSetupAutoPayment(@RequestBody AutoSetupDTO autoSetupDTO) {
		ResponseEntity<String> autoSetup = null;

		if (autoSetupDTO != null) {
			autoSetup = autoSetupService.addSetupAutoPayment(autoSetupDTO);
		}
		
		return autoSetup;

	}

}
