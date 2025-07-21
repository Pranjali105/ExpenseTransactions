package com.example.expense_transactions.service;

import org.springframework.http.ResponseEntity;

public interface MasterTableService {
	
	ResponseEntity<String> addCategoryAndSubCategory(String category, String subCategory);

}
