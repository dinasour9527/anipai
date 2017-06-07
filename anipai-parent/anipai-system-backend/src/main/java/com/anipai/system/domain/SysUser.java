package com.anipai.system.domain;

import com.anipai.agency.domain.Agency;

public class SysUser {
	private Long userId;
	private String userName;
	private String password;
	private SysRole sysRole;
	private Agency agency;
	
	public SysUser() {}
	public SysUser(String userName, String password, SysRole sysRole, Agency agency) {
		this.userName = userName;
		this.password = password;
		this.sysRole = sysRole;
		this.agency = agency;
	}
	public SysUser(Long userId, String userName, String password, SysRole sysRole, Agency agency) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.sysRole = sysRole;
		this.agency = agency;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
}
