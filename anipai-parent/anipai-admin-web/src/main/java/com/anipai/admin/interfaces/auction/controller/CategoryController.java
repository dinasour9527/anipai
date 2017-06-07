package com.anipai.admin.interfaces.auction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.auction.dto.CategoryDTO;
import com.anipai.admin.interfaces.auction.dto.CategoryDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.auction.domain.Category;
import com.anipai.auction.service.CategoryService;
import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/categoryManage", method = RequestMethod.GET)
	public String categoryManage() {
		return "auction/categoryManage";
	}
	
	@RequestMapping(value = "/categoryTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<CategoryDTO> categoryTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit, Integer level, Long parentCategoryId) {
		PageModel<CategoryDTO> pageModel = new PageModel<CategoryDTO>();
		Map<String, Object> categoryListPage = categoryService.getCategoryListPage(sort, order, offset, limit,
				currentUser.getAgencyId(), level, parentCategoryId);
		@SuppressWarnings("unchecked")
		List<Category> categoryList = (List<Category>) categoryListPage.get("list");
		List<CategoryDTO> categoryDTOList = CategoryDTOConverter.toDTOList(categoryList);
		pageModel
			.setRows(categoryDTOList)
			.setTotal((int) categoryListPage.get("total"));
		return pageModel;
	}
	
	@RequestMapping(value = "/categoryEditForm/{categoryId}", method = RequestMethod.GET)
	@ResponseBody
	public CategoryDTO categoryEditForm(@PathVariable Long categoryId) {
		Category category = categoryService.getCategory(categoryId);
		CategoryDTO categoryDTO = CategoryDTOConverter.toDTO(category);
		return categoryDTO;
	}
	
	@RequestMapping(value = "/categoryCreate", method = RequestMethod.POST)
	@ResponseBody
	public int categoryCreate(@CurrentUser SysUserDetails currentUser, String categoryName, Integer level,
			Long parentCategoryId) {
		categoryService.createCategory(categoryName, level, parentCategoryId, currentUser.getAgencyId());
		return 1;
	}
	
	@RequestMapping(value = "/categoryEdit", method = RequestMethod.POST)
	@ResponseBody
	public int categoryEdit(Long categoryId, String categoryName) {
		categoryService.editCategory(categoryId, categoryName);
		return 1;
	}
	
	@RequestMapping(value = "/categoryDelete", method = RequestMethod.POST)
	@ResponseBody
	public int categoryDelete(Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return 1;
	}
	
	@RequestMapping(value = "/listByLevel/{level}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryDTO> categoryListByLevel(@CurrentUser SysUserDetails currentUser,
			@PathVariable("level") Integer level) {
		List<Category> categoryList = categoryService.getCategoryListByLevel(level, currentUser.getAgencyId());
		List<CategoryDTO> categoryDTOList = CategoryDTOConverter.toDTOList(categoryList);
		return categoryDTOList;
	}
	
	@RequestMapping(value = "/listByParentCategoryId/{parentCategoryId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryDTO> categoryListByParentCategoryId(@CurrentUser SysUserDetails currentUser,
			@PathVariable("parentCategoryId") Long parentCategoryId) {
		List<Category> categoryList = categoryService.getCategoryListByParentCategoryId(parentCategoryId,
				currentUser.getAgencyId());
		List<CategoryDTO> categoryDTOList = CategoryDTOConverter.toDTOList(categoryList);
		return categoryDTOList;
	}
}
