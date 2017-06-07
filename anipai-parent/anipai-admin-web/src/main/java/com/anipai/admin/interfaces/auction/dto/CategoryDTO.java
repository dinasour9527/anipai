package com.anipai.admin.interfaces.auction.dto;

public class CategoryDTO {
	private Long categoryId;
	private String categoryName;
	//private Integer level;
	//private Long parentCategoryId;
	
	public CategoryDTO() {}
	public CategoryDTO(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
