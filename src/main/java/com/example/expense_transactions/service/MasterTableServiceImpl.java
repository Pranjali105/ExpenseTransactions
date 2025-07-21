package com.example.expense_transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.expense_transactions.repository.MasterTableRepository;

public class MasterTableServiceImpl implements MasterTableService {

	@Autowired
	MasterTableRepository masterTableRepository;

	@Override
	public ResponseEntity<String> addCategoryAndSubCategory(String category, String subCategory) {

		masterTableRepository.addCategory(category);

		int m = masterTableRepository.addSubCategory(category, subCategory);

		if (m == 0) {
			return ResponseEntity.ok("Error occured while inserting the data");
		} else
			return ResponseEntity.ok("Data inserted successfully");

	}

}
