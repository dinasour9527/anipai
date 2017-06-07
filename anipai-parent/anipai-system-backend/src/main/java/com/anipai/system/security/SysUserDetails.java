package com.anipai.system.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SysUserDetails extends User {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long agencyId;
	private String roleName;
	
	public SysUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long userId, Long agencyId, String roleName) {
		super(username, password, authorities);
		this.userId = userId;
		this.agencyId = agencyId;
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public String getRoleName() {
		return roleName;
	}

}
