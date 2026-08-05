package com.example.expense_transactions.service;

import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.dto.AutoSetupDTO;

public interface autoSetupService{

	ResponseEntity<String> addSetupAutoPayment(AutoSetupDTO autoSetupDTO);

}
