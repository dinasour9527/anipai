package com.anipai.admin.interfaces.system.dto;

public class RoleDTO {
	private Long roleId;
	private String roleCode;
	private String roleName;
	
	public RoleDTO() {}
	public RoleDTO(Long roleId, String roleCode, String roleName) {
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public RoleDTO setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public RoleDTO setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}
	public String getRoleName() {
		return roleName;
	}
	public RoleDTO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
}
