package com.anipai.system.service;

import java.util.Map;

import com.anipai.system.domain.SysUser;

public interface UserService {

	SysUser getUser(Long userId);

	Map<String, Object> getUserListPage(String sort, String order, int offset, int limit, Long agencyId);

	Long createUser(String userName, Long roleId, Long agencyId);

	void editUser(Long userId, String userName, Long roleId);

	void deleteUser(Long userId);

}
