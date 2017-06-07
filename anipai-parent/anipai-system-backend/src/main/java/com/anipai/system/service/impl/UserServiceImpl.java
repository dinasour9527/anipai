package com.anipai.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.system.domain.SysRole;
import com.anipai.system.domain.SysUser;
import com.anipai.system.mapper.UserMapper;
import com.anipai.system.service.UserService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public SysUser getUser(Long userId) {
		SysUser user = userMapper.findUser(userId);
		return user;
	}

	@Override
	public Map<String, Object> getUserListPage(String sort, String order, int offset, int limit,
			Long agencyId) {
		Map<String, Object> userListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<SysUser> userList = userMapper.findUserPage(pagination, agencyId);
		int total = userMapper.total(agencyId);
		userListPage.put("list", userList);
		userListPage.put("total", total);
		return userListPage;
	}

	@Override
	public Long createUser(String userName, Long roleId, Long agencyId) {
		String password = "123f38e451e477a974f27fb133f96a8d";
		SysRole role = new SysRole(roleId, null, null, null);
		Agency agency = new Agency(agencyId, null);
		SysUser user = new SysUser(userName, password, role, agency);
		userMapper.addUser(user);
		return user.getUserId();
	}

	@Override
	public void editUser(Long userId, String userName, Long roleId) {
		SysRole role = new SysRole(roleId, null, null, null);
		SysUser user = new SysUser(userId, userName, null, role, null);
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userMapper.deleteUser(userId);
	}

}
