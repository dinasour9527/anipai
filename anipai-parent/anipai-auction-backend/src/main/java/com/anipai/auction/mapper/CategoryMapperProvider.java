package com.anipai.auction.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class CategoryMapperProvider {

	public String findCategoryPage(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		Long parentCategoryId = (Long)map.get("parentCategoryId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select category_id, category_name from category ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("and level=#{level} ");
		if(parentCategoryId != null) {
			sb.append("and parent_category_id=#{parentCategoryId} ");
		} else {
			sb.append("and parent_category_id is null ");
		}
		sb.append("order by " + page.getSort() + " " + page.getOrder() + " ");
		sb.append("limit #{page.offset}, #{page.limit}");
		return sb.toString();
	}
	
	public String total(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		Long parentCategoryId = (Long)map.get("parentCategoryId");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(category_id) from category ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("and level=#{level} ");
		if(parentCategoryId != null) {
			sb.append("and parent_category_id=#{parentCategoryId}");
		} else {
			sb.append("and parent_category_id is null");
		}
		return sb.toString();
	}
	
	public String findCategoryByLevel(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select category_id, category_name from category ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("and level=#{level} ");
		return sb.toString();
	}
	
	public String findCategoryByParentCategoryId(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select category_id, category_name from category ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("and parent_category_id=#{parentCategoryId} ");
		return sb.toString();
	}
	
	public String findAll(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select category_id, category_name, level, parent_category_id from category ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
