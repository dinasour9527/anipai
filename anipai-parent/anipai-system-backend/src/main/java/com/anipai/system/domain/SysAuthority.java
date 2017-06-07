package com.anipai.system.domain;

public class SysAuthority {
	private Long authorityId;
	private String authorityCode;
	private String authorityName;
	
	public SysAuthority() {}
	public SysAuthority(String authorityCode, String authorityName) {
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
	}
	public SysAuthority(Long authorityId, String authorityCode, String authorityName) {
		this.authorityId = authorityId;
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
	}
	
	public Long getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	
}
