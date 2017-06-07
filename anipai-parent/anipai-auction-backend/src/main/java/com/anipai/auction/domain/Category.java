package com.anipai.auction.domain;

import com.anipai.agency.domain.Agency;

public class Category {
	private Long categoryId;
	private String categoryName;
	/*分类级别*/
	private Integer level;
	/*父级分类*/
	private Category parentCategory;
	private Agency agency;
	
	public Category() {}
	public Category(String categoryName, Integer level, Category parentCategory, Agency agency) {
		this.categoryName = categoryName;
		this.level = level;
		this.parentCategory = parentCategory;
		this.agency = agency;
	}
	public Category(Long categoryId, String categoryName, Integer level, Category parentCategory, Agency agency) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.level = level;
		this.parentCategory = parentCategory;
		this.agency = agency;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
}
