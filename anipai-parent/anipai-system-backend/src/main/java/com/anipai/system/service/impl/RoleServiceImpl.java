package com.anipai.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.system.domain.SysRole;
import com.anipai.system.domain.SysRoleAuthority;
import com.anipai.system.mapper.RoleAuthorityMapper;
import com.anipai.system.mapper.RoleMapper;
import com.anipai.system.service.RoleService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleAuthorityMapper roleAuthorityMapper;

	@Override
	public SysRole getRole(Long roleId) {
		SysRole sysRole = roleMapper.findRole(roleId);
		return sysRole;
	}

	@Override
	public Map<String, Object> getRoleListPage(String sort, String order, int offset, int limit,
			Long agencyId) {
		Map<String, Object> roleListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit/*, agencyId*/);
		List<SysRole> roleList = roleMapper.findRolePage(pagination, agencyId);
		int total = roleMapper.total(agencyId);
		roleListPage.put("list", roleList);
		roleListPage.put("total", total);
		return roleListPage;
	}

	@Override
	public List<SysRole> getRoleList(Long agencyId) {
		List<SysRole> roleList = roleMapper.findAllByAgencyId(agencyId);
		return roleList;
	}

	@Override
	public Long createRole(String roleCode, String roleName, Long agencyId) {
		Agency agency = new Agency(agencyId, null);
		SysRole sysRole = new SysRole(roleCode, roleName, agency);
		roleMapper.addRole(sysRole);
		return sysRole.getRoleId();
	}

	@Override
	public void editRole(Long roleId, String roleCode, String roleName) {
		SysRole sysRole = new SysRole(roleId, roleCode, roleName, null);
		roleMapper.updateRole(sysRole);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleAuthorityMapper.deleteByRoleId(roleId);
		roleMapper.deleteRole(roleId);
	}

	@Override
	public void relateAuthority(Long roleId, Long[] authorityIds) {
		List<SysRoleAuthority> sysRoleAuthorityList = new ArrayList<SysRoleAuthority>();
		roleAuthorityMapper.deleteByRoleId(roleId);
		for(int i = 0; i < authorityIds.length; i++) {
			SysRoleAuthority sysRoleAuthority = new SysRoleAuthority(roleId, authorityIds[i]);
			sysRoleAuthorityList.add(sysRoleAuthority);
		}
		roleAuthorityMapper.insertAll(sysRoleAuthorityList);
	}

}
