package com.example.expense_transactions.dto;

public class CategorySubCategoryDTO {

	private String category;

	private String subCategory;

	public CategorySubCategoryDTO(String category, String subCategory) {
		super();
		this.category = category;
		this.subCategory = subCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

}
