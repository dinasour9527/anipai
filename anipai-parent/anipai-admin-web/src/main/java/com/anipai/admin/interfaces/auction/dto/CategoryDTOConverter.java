package com.anipai.admin.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Category;

public class CategoryDTOConverter {
	public static CategoryDTO toDTO(Category category) {
		return new CategoryDTO(category.getCategoryId(), category.getCategoryName());
	}
	
	public static List<CategoryDTO> toDTOList(List<Category> categoryList) {
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		for(Category category : categoryList){
			categoryDTOList.add(CategoryDTOConverter.toDTO(category));
		}
		return categoryDTOList;
	}
}
