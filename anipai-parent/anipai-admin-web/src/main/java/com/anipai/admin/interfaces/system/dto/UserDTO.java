package com.anipai.admin.interfaces.system.dto;

public class UserDTO {
	
	private Long userId;
	private String userName;
	private Long roleId;
	private String roleName;
	
	public UserDTO() {}
	public UserDTO(Long userId, String userName, Long roleId, String roleName) {
		this.userId = userId;
		this.userName = userName;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
