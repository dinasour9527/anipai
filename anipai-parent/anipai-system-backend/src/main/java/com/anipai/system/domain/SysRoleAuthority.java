package com.anipai.system.domain;

public class SysRoleAuthority {
	private Long id;
	private Long roleId;
	private Long authorityId;
	
	public SysRoleAuthority() {}
	public SysRoleAuthority(Long roleId, Long authorityId) {
		this.roleId = roleId;
		this.authorityId = authorityId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}
	
}
