package com.anipai.system.domain;

import com.anipai.agency.domain.Agency;

public class SysRole {
	private Long roleId;
	private String roleCode;
	private String roleName;
	private Agency agency;
	
	public SysRole() {}
	public SysRole(String roleCode, String roleName, Agency agency) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.agency = agency;
	}
	public SysRole(Long roleId, String roleCode, String roleName, Agency agency) {
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.agency = agency;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
}
