package com.anipai.admin.interfaces.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.system.dto.RoleDTO;
import com.anipai.admin.interfaces.system.dto.RoleDTOConverter;
import com.anipai.admin.interfaces.system.dto.UserDTO;
import com.anipai.admin.interfaces.system.dto.UserDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.system.domain.SysAuthority;
import com.anipai.system.domain.SysRole;
import com.anipai.system.domain.SysUser;
import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;
import com.anipai.system.service.AuthorityService;
import com.anipai.system.service.RoleService;
import com.anipai.system.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/roleManage", method = RequestMethod.GET)
	public String roleManage() {
		return "sys/roleManage";
	}
	
	@RequestMapping(value = "/userManage", method = RequestMethod.GET)
	public String userManage() {
		return "sys/userManage";
	}
	
	@RequestMapping(value = "/roleTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<RoleDTO> roleTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit) {
		Map<String, Object> roleListPage = roleService.getRoleListPage(sort, order, offset, limit,
				currentUser.getAgencyId());
		@SuppressWarnings("unchecked")
		List<SysRole> roleList = (List<SysRole>) roleListPage.get("list");
		PageModel<RoleDTO> pageModel = new PageModel<RoleDTO>();
		List<RoleDTO> roleDTOList = RoleDTOConverter.toDTOList(roleList);
		pageModel
			.setRows(roleDTOList)
			.setTotal((int) roleListPage.get("total"));
		return pageModel;
	}
	
	@RequestMapping(value = "/roleEditForm/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> roleEditForm(@PathVariable Long roleId) {
		Map<String, Object> resultModel = new HashMap<String, Object>();
		SysRole role = roleService.getRole(roleId);
		List<SysAuthority> authorityList = authorityService.getAuthorityListByRoleId(roleId);
		resultModel.put("role", role);
		resultModel.put("authorities", authorityList);
		return resultModel;
	}
	
	@RequestMapping(value = "/authority", method = RequestMethod.GET)
	@ResponseBody
	public List<SysAuthority> authority() {
		List<SysAuthority> authorityList = authorityService.getAuthorityList();
		return authorityList;
	}
	
	@RequestMapping(value = "/roleCreate", method = RequestMethod.POST)
	@ResponseBody
	public int roleCreate(@CurrentUser SysUserDetails currentUser,
			String roleCode, String roleName, Long[] authorityIds) {
		Long roleId = roleService.createRole(roleCode, roleName, currentUser.getAgencyId());
		roleService.relateAuthority(roleId, authorityIds);
		return 1;
	}
	
	@RequestMapping(value = "/roleEdit", method = RequestMethod.POST)
	@ResponseBody
	public int roleEdit(Long roleId, String roleCode, String roleName, Long[] authorityIds) {
		roleService.editRole(roleId, roleCode, roleName);
		roleService.relateAuthority(roleId, authorityIds);
		return 1;
	}
	
	@RequestMapping(value = "/roleDelete", method = RequestMethod.POST)
	@ResponseBody
	public int roleDelete(Long roleId) {
		roleService.deleteRole(roleId);
		return 1;
	}
	
	@RequestMapping(value = "/userTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<UserDTO> userTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit) {
		Map<String, Object> userListPage = userService.getUserListPage(sort, order, offset, limit,
				currentUser.getAgencyId());
		@SuppressWarnings("unchecked")
		List<SysUser> userList = (List<SysUser>) userListPage.get("list");
		PageModel<UserDTO> pageModel = new PageModel<UserDTO>();
		List<UserDTO> userDTOList = UserDTOConverter.toDTOList(userList);
		pageModel
			.setRows(userDTOList)
			.setTotal((int) userListPage.get("total"));
		return pageModel;
	}
	
	@RequestMapping(value = "/userEditForm/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO userEditForm(@PathVariable Long userId) {
		SysUser user = userService.getUser(userId);
		UserDTO userDTO = UserDTOConverter.toDTO(user);
		return userDTO;
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleDTO> role(@CurrentUser SysUserDetails currentUser) {
		List<SysRole> roleList = roleService.getRoleList(currentUser.getAgencyId());
		List<RoleDTO> roleDTOList = RoleDTOConverter.toDTOList(roleList);
		return roleDTOList;
	}
	
	@RequestMapping(value = "/userCreate", method = RequestMethod.POST)
	@ResponseBody
	public int userCreate(@CurrentUser SysUserDetails currentUser,
			String userName, Long roleId) {
		userService.createUser(userName, roleId, currentUser.getAgencyId());
		return 1;
	}
	
	@RequestMapping(value = "/userEdit", method = RequestMethod.POST)
	@ResponseBody
	public int userEdit(Long userId, String userName, Long roleId) {
		userService.editUser(userId, userName, roleId);
		return 1;
	}
	
	@RequestMapping(value = "/userDelete", method = RequestMethod.POST)
	@ResponseBody
	public int userDelete(Long userId) {
		userService.deleteUser(userId);
		return 1;
	}
}
