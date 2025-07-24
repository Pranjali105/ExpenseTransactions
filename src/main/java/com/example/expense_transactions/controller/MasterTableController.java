package com.example.expense_transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.CategorySubCategoryDTO;
import com.example.expense_transactions.service.MasterTableService;

@RestController
public class MasterTableController {

	@Autowired
	MasterTableService masterTableService;

	@PostMapping(value = "/addCategoryAndSubCategory")
	public ResponseEntity<String> addCategoryAndSubCategory(
			@RequestBody CategorySubCategoryDTO categorySubCategoryDTO) {

		ResponseEntity<String> categorySubCategory = null;

		if (categorySubCategoryDTO.getCategory() != null && categorySubCategoryDTO.getSubCategory() != null) {
			categorySubCategory = masterTableService.addCategoryAndSubCategory(categorySubCategoryDTO.getCategory(),
					categorySubCategoryDTO.getSubCategory());
		}
		return categorySubCategory;
	}

}
