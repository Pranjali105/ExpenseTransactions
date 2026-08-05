package com.example.expense_transactions.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AutoSetupDTO;

@Service
public class autoSetupServiceImpl implements autoSetupService {

	@Override
	public ResponseEntity<String> addSetupAutoPayment(AutoSetupDTO autoSetupDTO) {
		
		
		
		return ResponseEntity.ok("Data inserted successfully");
	}

}
