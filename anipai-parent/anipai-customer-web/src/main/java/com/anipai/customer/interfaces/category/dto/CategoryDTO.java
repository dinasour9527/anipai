package com.anipai.customer.interfaces.category.dto;

public class CategoryDTO {
	private Long categoryId;
	private String categoryName;
	private Long parentCategoryId;
	private Integer level;
	//private Integer level;
	//private Long parentCategoryId;
	
	public CategoryDTO() {}
	public CategoryDTO(Long categoryId, String categoryName, Long parentCategoryId, Integer level) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentCategoryId = parentCategoryId;
		this.level = level;
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
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
