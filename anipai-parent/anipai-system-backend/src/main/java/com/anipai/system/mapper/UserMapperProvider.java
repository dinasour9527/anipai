package com.anipai.system.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class UserMapperProvider {
	
	@SuppressWarnings("rawtypes")
	public String findUserPage(Map map) {
		Long agencyId = (Long)map.get("agencyId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select user_id, user_name, role_id from sys_user ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId} ");
		} else {
			sb.append("where agency_id is null ");
		}
		sb.append("order by " + page.getSort() + " " + page.getOrder() + " ");
		sb.append("limit #{page.offset}, #{page.limit}");
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String total(Map map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select count(user_id) from sys_user ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
