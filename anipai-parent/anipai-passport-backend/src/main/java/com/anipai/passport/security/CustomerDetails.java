package com.anipai.passport.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomerDetails extends User {
	private static final long serialVersionUID = 1L;
	
	private Long customerId;
	private Long agencyId;

	public CustomerDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long customerId, Long agencyId) {
		super(username, password, authorities);
		this.customerId = customerId;
		this.agencyId = agencyId;
	}

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
}
