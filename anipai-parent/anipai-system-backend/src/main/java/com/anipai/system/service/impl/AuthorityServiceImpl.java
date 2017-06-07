package com.anipai.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.system.domain.SysAuthority;
import com.anipai.system.mapper.AuthorityMapper;
import com.anipai.system.service.AuthorityService;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public List<SysAuthority> getAuthorityList() {
		List<SysAuthority> authorityList = authorityMapper.findAll();
		return authorityList;
	}

	@Override
	public List<SysAuthority> getAuthorityListByRoleId(Long roleId) {
		List<SysAuthority> authorityList = authorityMapper.findAuthorityByRoleId(roleId);
		return authorityList;
	}

}
