package com.example.expense_transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.AutoSetupPaymentDTO;
import com.example.expense_transactions.service.AutoSetupService;

@RestController
public class AutoSetupPaymentController {

	@Autowired
	AutoSetupService autoSetupService;

	ResponseEntity<String> autoSetup = null;

	@PostMapping(value = "/addSetupAutoPaymentDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSetupAutoPaymentDetails(@RequestBody AutoSetupPaymentDTO autoSetupPaymentDTO) {

		if (autoSetupPaymentDTO != null) {
			autoSetup = autoSetupService.addSetupAutoPaymentDetails(autoSetupPaymentDTO);
		}

		return autoSetup;

	}

	
	  @PostMapping(value = "/addSetupAutoPayment", consumes =
	  MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<String>
	  addSetupAutoPayment(@RequestBody AutoSetupPaymentDTO autoSetupPaymentDTO) {
	  
	  if (autoSetupPaymentDTO != null) { autoSetup =
	  autoSetupService.addSetupAutoPayment(autoSetupPaymentDTO); }
	  
	  return ResponseEntity.ok("Auto Setup Payment Details Added Successfully"); }
	 

}
