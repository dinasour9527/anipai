package com.anipai.system.mapper;

import java.util.Map;

import com.anipai.utils.Pagination;

public class RoleMapperProvider {

	@SuppressWarnings("rawtypes")
	public String findRolePage(Map map) {
		Long agencyId = (Long)map.get("agencyId");
		Pagination page = (Pagination)map.get("page");
		StringBuilder sb = new StringBuilder();
		sb.append("select role_id, role_code, role_name from sys_role ");
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
		sb.append("select count(role_id) from sys_role ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
	
	public String findAllByAgencyId(Map<String, Object> map) {
		Long agencyId = (Long)map.get("agencyId");
		StringBuilder sb = new StringBuilder();
		sb.append("select role_id, role_code, role_name from sys_role ");
		if(agencyId != null) {
			sb.append("where agency_id=#{agencyId}");
		} else {
			sb.append("where agency_id is null");
		}
		return sb.toString();
	}
}
