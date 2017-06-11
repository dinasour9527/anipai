package com.anipai.customer.interfaces.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.auction.domain.Category;
import com.anipai.auction.service.CategoryService;
import com.anipai.customer.interfaces.category.dto.CategoryDTO;
import com.anipai.customer.interfaces.category.dto.CategoryDTOConverter;
import com.anipai.passport.security.CurrentCustomer;
import com.anipai.passport.security.CustomerDetails;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/menu")
	@ResponseBody
	public List<CategoryDTO> menu(@CurrentCustomer CustomerDetails currentCustomer) {
		List<Category> categoryMenu = categoryService.getCategoryMenu(new Long(1));
		List<CategoryDTO> dtoList = CategoryDTOConverter.toDTOList(categoryMenu);
		return dtoList;
	}
}
