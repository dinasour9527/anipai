package com.anipai.auction.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.auction.domain.Category;
import com.anipai.auction.mapper.CategoryMapper;
import com.anipai.auction.service.CategoryService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Map<String, Object> getCategoryListPage(String sort, String order, int offset, int limit,
			Long agencyId, Integer level, Long parentCategoryId) {
		Map<String, Object> categoryListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<Category> categoryList = categoryMapper.findCategoryPage(pagination, agencyId, level, parentCategoryId);
		int total = categoryMapper.total(agencyId, level, parentCategoryId);
		categoryListPage.put("list", categoryList);
		categoryListPage.put("total", total);
		return categoryListPage;
	}

	@Override
	public Long createCategory(String categoryName, Integer level, Long parentCategoryId, Long agencyId) {
		Category parentCategory = new Category(parentCategoryId, null, null, null, null);
		Agency agency = new Agency(agencyId, null);
		Category category = new Category(categoryName, level, parentCategory, agency);
		categoryMapper.addCategory(category);
		return category.getCategoryId();
	}

	@Override
	public Category getCategory(Long categoryId) {
		Category category = categoryMapper.findCategory(categoryId);
		return category;
	}

	@Override
	public void editCategory(Long categoryId, String categoryName) {
		Category category = new Category(categoryId, categoryName, null, null, null);
		categoryMapper.updateCategory(category);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryMapper.deleteCategory(categoryId);
	}

	@Override
	public List<Category> getCategoryListByLevel(Integer level, Long agencyId) {
		List<Category> categoryList = categoryMapper.findCategoryByLevel(level, agencyId);
		return categoryList;
	}

	@Override
	public List<Category> getCategoryListByParentCategoryId(Long parentCategoryId, Long agencyId) {
		List<Category> categoryList = categoryMapper.findCategoryByParentCategoryId(parentCategoryId, agencyId);
		return categoryList;
	}
	
}
