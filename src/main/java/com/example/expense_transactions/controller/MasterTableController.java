package com.example.expense_transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.service.MasterTableService;

@RestController
public class MasterTableController {

	@Autowired
	MasterTableService masterTableService;

	@PostMapping(value = "/addCategoryAndSubCategory")
	public ResponseEntity<String> addCategoryAndSubCategory(@RequestParam(required = true) String category,
			@RequestParam(required = true) String subCategory) {

		return masterTableService.addCategoryAndSubCategory(category, subCategory);

	}

}
