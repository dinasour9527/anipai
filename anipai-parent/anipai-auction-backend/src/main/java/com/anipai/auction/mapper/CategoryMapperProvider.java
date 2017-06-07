package com.anipai.auction.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class CategoryMapperProvider {

	@SuppressWarnings("rawtypes")
	public String findCategoryPage(Map map) {
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
	
	@SuppressWarnings("rawtypes")
	public String total(Map map) {
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
	
	@SuppressWarnings("rawtypes")
	public String findCategoryByLevel(Map map) {
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
	
	@SuppressWarnings("rawtypes")
	public String findCategoryByParentCategoryId(Map map) {
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
}
