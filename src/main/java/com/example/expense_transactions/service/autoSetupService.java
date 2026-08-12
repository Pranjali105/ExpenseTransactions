package com.example.expense_transactions.service;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.AutoSetupPaymentDTO;

public interface AutoSetupService {

	ResponseEntity<String> addSetupAutoPaymentDetails(AutoSetupPaymentDTO autoSetupPaymentDTO);

	ResponseEntity<String> addSetupAutoPayment(AutoSetupPaymentDTO autoSetupPaymentDTO);

}
