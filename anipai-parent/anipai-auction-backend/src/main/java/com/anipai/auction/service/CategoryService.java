package com.anipai.auction.service;

import java.util.List;
import java.util.Map;

import com.anipai.auction.domain.Category;

public interface CategoryService {

	Map<String, Object> getCategoryListPage(String sort, String order, int offset, int limit,
			Long agencyId, Integer level, Long parentCategoryId);

	Category getCategory(Long categoryId);

	List<Category> getCategoryListByLevel(Integer level, Long agencyId);

	List<Category> getCategoryListByParentCategoryId(Long parentCategoryId, Long agencyId);

	Long createCategory(String categoryName, Integer level, Long parentCategoryId, Long agencyId);

	void editCategory(Long categoryId, String categoryName);

	void deleteCategory(Long categoryId);

}
