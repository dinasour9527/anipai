package com.anipai.customer.interfaces.auction.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.auction.domain.Category;

public class CategoryDTOConverter {
	public static CategoryDTO toDTO(Category category) {
		Long parentCategoryId = null;
		if(category.getParentCategory()!=null) {
			parentCategoryId = category.getParentCategory().getCategoryId();
		}
		return new CategoryDTO(category.getCategoryId(), category.getCategoryName(),
				parentCategoryId, category.getLevel());
	}
	
	public static List<CategoryDTO> toDTOList(List<Category> categoryList) {
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		for(Category category : categoryList){
			categoryDTOList.add(CategoryDTOConverter.toDTO(category));
		}
		return categoryDTOList;
	}
}
