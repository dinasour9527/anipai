package com.anipai.system.service;

import java.util.List;
import java.util.Map;

import com.anipai.system.domain.SysRole;

public interface RoleService {

	SysRole getRole(Long roleId);

	Map<String, Object> getRoleListPage(String sort, String order, int offset, int limit, Long agencyId);

	List<SysRole> getRoleList(Long agencyId);

	Long createRole(String roleCode, String roleName, Long agencyId);

	void editRole(Long roleId, String roleCode, String roleName);

	void deleteRole(Long roleId);

	void relateAuthority(Long roleId, Long[] authorityIds);
	
	
}
