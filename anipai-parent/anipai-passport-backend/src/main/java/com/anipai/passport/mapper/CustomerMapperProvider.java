package com.anipai.passport.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class CustomerMapperProvider {

	public String findCustomerPage(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select customer_id, customer_name, email "
				+ "from customer ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("order by " + page.getSort() + " " + page.getOrder() + " ");
		sb.append("limit #{page.offset}, #{page.limit}");
		return sb.toString();
	}
	
	public String total(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(customer_id) from customer ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
