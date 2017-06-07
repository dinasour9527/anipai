package com.anipai.system.service;

import java.util.List;

import com.anipai.system.domain.SysAuthority;

public interface AuthorityService {

	List<SysAuthority> getAuthorityList();

	List<SysAuthority> getAuthorityListByRoleId(Long roleId);
	
}
